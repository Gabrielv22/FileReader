package task;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class FileValidator {

    private final FileReader fileReader;

    public FileValidator(task.FileReader fileReader) {
        this.fileReader = fileReader;
    }

    public static void main(String[] args) throws IOException {
        FileValidator validator = new FileValidator(new FileReader());
        validator.filterFile(args[0], args[1], args[2], args[3]);
    }

    void filterFile(String textPath,
                    String linesToPrint,
                    String filterOperation,
                    String filterArg) throws IOException {
        String file = fileReader.readFile(textPath);
        List<String> filteredText = applyFiltering(file, linesToPrint, filterOperation, filterArg);
        printResult(filteredText);
    }

    List<String> applyFiltering(String text,
                                String linesToPrint) {
        String[] lines = text.split(System.getProperty(task.Constants.LINE_SEPARATOR));
        return filterLinesToPrintOnly(lines, linesToPrint);
    }

    List<String> applyFiltering(String text,
                                String linesToPrint,
                                String filterOperation,
                                String filterArg) {
        List<String> lastLinesToPrint = applyFiltering(text, linesToPrint);
        if (filterOperation != null) {
            return applyFilterOperations(lastLinesToPrint, filterOperation, filterArg);
        }
        return lastLinesToPrint;
    }

    private List<String> filterLinesToPrintOnly(String[] lines, String linesToPrint) {
        int numberLines = Integer.parseInt(linesToPrint);
        return
                Arrays
                        .stream(lines)
                        .skip(lines.length - numberLines)
                        .collect(Collectors.toList());
    }

    private List<String> applyFilterOperations(List<String> text,
                                               String filterOperation,
                                               String filterArg) {
        List<String> result = new ArrayList<>();
        boolean lastLineMatched = false;

        for (int i = 1; i < text.size(); i++) {
            if (filterMatches(filterOperation, filterArg, text.get(i))) {
                result.add(text.get(i - 1));
                lastLineMatched = true;
            } else {
                if (lastLineMatched) {
                    result.add(text.get(i - 1));
                }
                lastLineMatched = false;
            }
        }

        return result;
    }

    private boolean filterMatches(String filterOperation, String filterArg, String fileLine) {
        switch (filterOperation) {
            case "-startsWith":
                return fileLine.startsWith(filterArg);
            case "-endsWith":
                return fileLine.endsWith(filterArg);
            case "-contains":
                return fileLine.contains(filterArg);
            default:
                throw new InvalidFilterOperationException("Invalid filter operation parameter. Unable to progress further");
        }
    }

    private void printResult(List<String> filteredText) {
        System.out.println(Arrays.toString(filteredText.toArray()));
    }

}
