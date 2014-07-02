package eu.rapasoft.service;

import eu.rapasoft.exception.ImageFileException;
import eu.rapasoft.model.ColorFrequency;
import eu.rapasoft.util.ColorComparator;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.util.*;
import java.util.List;
import java.util.concurrent.*;
import java.util.logging.Logger;

/**
 * @author Pavol Rajzak, www.rapasoft.eu
 */
@ApplicationScoped
public class FrequencyAnalyzerService {

    private static Logger logger = Logger.getLogger(FrequencyAnalyzerService.class.getName());

    @Inject
    ImageService imageService;

    public SortedSet<ColorFrequency> computeFrequencies(File file) throws ImageFileException {
        BufferedImage image = imageService.loadBufferedImage(file);
        try {
            return calculateFrequenciesPerColor(image);
        } catch (ExecutionException | InterruptedException e) {
            e.printStackTrace();
        }
        throw new RuntimeException();
    }

    private SortedSet<ColorFrequency> calculateFrequenciesPerColor(BufferedImage image) throws ExecutionException, InterruptedException {
        int numberOfThreads = Runtime.getRuntime().availableProcessors();

        final ExecutorService service = Executors.newFixedThreadPool(numberOfThreads);

        List<Future<Set<ColorFrequency>>> futures = new ArrayList<>();
        for (int i = 0; i < numberOfThreads; i++) {
            Callable<Set<ColorFrequency>> call = new FrequencyCalculator(image, i, numberOfThreads);
            Future<Set<ColorFrequency>> result = service.submit(call);
            futures.add(result);
        }

        Set<ColorFrequency> resultSet = new LinkedHashSet<>();
        for (Future<Set<ColorFrequency>> future : futures) {
            Set<ColorFrequency> colorFrequenciesPartial = future.get();
            logger.info("Merging " + resultSet.size() + " existing results with " + colorFrequenciesPartial.size() + " unprocessed");
            for (ColorFrequency colorFrequencyPartial : colorFrequenciesPartial) {
                if (!resultSet.contains(colorFrequencyPartial)) {
                    resultSet.add(colorFrequencyPartial);
                } else {
                    for (ColorFrequency colorFrequencyTotal : resultSet) {
                        if (colorFrequencyPartial.getColor().equals(colorFrequencyTotal.getColor())) {
                            colorFrequencyTotal.addNumberOfOccurrences(colorFrequencyPartial.getNumberOfOccurrences());
                        }
                    }
                }
            }
            logger.info("Merged results with new total size " + resultSet.size());
        }

        service.shutdown();

        SortedSet<ColorFrequency> sortedResultSet = new TreeSet<>(new ColorFrequency.ColorFrequencyHexStringComparator());
        sortedResultSet.addAll(resultSet);

        return sortedResultSet;
    }

    private static class FrequencyCalculator implements Callable<Set<ColorFrequency>> {

        private final BufferedImage image;
        private final int threadNumber;
        private final int threadTotal;

        private FrequencyCalculator(BufferedImage image, int threadNumber, int threadTotal) {
            this.image = image;
            this.threadNumber = threadNumber;
            this.threadTotal = threadTotal;


        }

        @Override
        public Set<ColorFrequency> call() throws Exception {
            logger.info("Starting thread number " + threadNumber + "/" + threadTotal);
            Set<ColorFrequency> frequencies = new HashSet<>();

            Map<Color, Integer> frequencyMap = new TreeMap<>(new ColorComparator());

            long totalPixelCount = image.getHeight() * image.getWidth();

            int partFrom = threadNumber * (image.getHeight() / threadTotal);
            int partTo = (threadNumber + 1) * (image.getHeight() / threadTotal) + (threadNumber == threadTotal - 1 ? image.getHeight() % threadTotal : 0);

            for (int x = 0; x < image.getWidth(); x++) {
                for (int y = partFrom; y < partTo; y++) {
                    int rgb = image.getRGB(x, y);
                    Color color = new Color(rgb);
                    if (frequencyMap.containsKey(color)) {
                        frequencyMap.put(color, frequencyMap.get(color) + 1);
                    } else {
                        frequencyMap.put(color, 1);
                    }
                }
            }

            for (Color color : frequencyMap.keySet()) {
                frequencies.add(new ColorFrequency(color, frequencyMap.get(color), totalPixelCount));
            }

            logger.info("Finished processing thread number " + threadNumber + "/" + threadTotal);

            return frequencies;
        }
    }

}
