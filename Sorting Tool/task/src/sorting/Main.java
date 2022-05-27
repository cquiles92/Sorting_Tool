package sorting;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(final String[] args) {
        execute(args);
    }

    //potentially move to a factory instead
    private static void execute(String[] args) {
        List<String> argumentList = new ArrayList<>(List.of(args));
        SortType sortType = getSortType(argumentList);
        DataType dataType = getDataType(argumentList);
        String inputFile = getInputFile(argumentList);
        String outputFile = getOutputFile(argumentList);
        DataCollector dataCollector;

        argumentTest(argumentList, sortType, dataType, inputFile, outputFile);
        dataCollector = getDataCollector(dataType);

        if (!inputFile.equals("")) {
            try (BufferedInputStream inputStream = new BufferedInputStream(new FileInputStream(inputFile))) {
                System.setIn(inputStream);
            } catch (IOException e) {
                System.out.println("Error: File \"" + inputFile + "\" not found.");
                throw new RuntimeException(e);
            }
        }

        if (!outputFile.equals("")) {
            File file = new File(outputFile);
            try {
                if (file.createNewFile()) {
                    try (PrintStream outputStream = new PrintStream(new BufferedOutputStream(new FileOutputStream(file)))) {
                        System.setOut(outputStream);
                    } catch (FileNotFoundException e) {
                        System.out.println("Error: Could not output to file: \"" + outputFile + "\".");
                        throw new RuntimeException(e);
                    }
                }
            } catch (IOException e) {
                System.out.println("Error: Could not create file: \"" + outputFile + "\".");
                throw new RuntimeException(e);
            }
        }

        executeSortStrategy(sortType, dataCollector);
    }


    private static SortType getSortType(List<String> arguments) {
        SortType sortType = null;
        if (arguments.contains("-" + CLI_Arguments.sortingType.name())) {
            int index = arguments.indexOf("-" + CLI_Arguments.sortingType.name()) + 1;
            if (index < arguments.size()) {
                for (SortType type : SortType.values()) {
                    if (type.name().equalsIgnoreCase(arguments.get(index))) {
                        sortType = type;
                        break;
                    }
                }
                arguments.remove(index);
                arguments.remove("-" + CLI_Arguments.sortingType.name());
            }
        } else {
            sortType = SortType.natural;//default value;
        }
        return sortType;
    }

    private static DataType getDataType(List<String> arguments) {
        DataType dataType = null;
        if (arguments.contains("-" + CLI_Arguments.dataType.name())) {
            int index = arguments.indexOf("-" + CLI_Arguments.dataType.name()) + 1;
            if (index < arguments.size()) {
                for (DataType type : DataType.values()) {
                    if (type.name().equalsIgnoreCase(arguments.get(index))) {
                        dataType = type;
                        break;
                    }
                }
                arguments.remove(index);
                arguments.remove("-" + CLI_Arguments.dataType.name());
            }
        } else {
            dataType = DataType.WORD;//default value
        }
        return dataType;
    }

    private static String getInputFile(List<String> arguments) {
        String inputFileName = null;
        if (arguments.contains("-" + CLI_Arguments.inputFile.name())) {
            int index = arguments.indexOf("-" + CLI_Arguments.inputFile.name()) + 1;
            if (index < arguments.size()) {
                inputFileName = arguments.get(index);
                arguments.remove(index);
                arguments.remove("-" + CLI_Arguments.inputFile.name());
            }
        } else {
            inputFileName = "";
        }
        return inputFileName;
    }

    private static String getOutputFile(List<String> arguments) {
        String outputFileName = null;
        if (arguments.contains("-" + CLI_Arguments.outputFile.name())) {
            int index = arguments.indexOf("-" + CLI_Arguments.outputFile.name()) + 1;
            if (index < arguments.size()) {
                outputFileName = arguments.get(index);
                arguments.remove(index);
                arguments.remove("-" + CLI_Arguments.outputFile.name());
            }
        } else {
            outputFileName = "";
        }
        return outputFileName;
    }

    private static DataCollector getDataCollector(DataType dataType) {
        DataCollector dataCollector;
        switch (dataType) {
            case LONG:
                dataCollector = new LongCollector();
                break;
            case LINE:
                dataCollector = new LineCollector();
                break;
            //default behaviour is word
            case WORD:
            default:
                dataCollector = new WordCollector();
        }
        return dataCollector;
    }

    private static void argumentTest(List<String> argumentList, SortType sortType, DataType dataType, String inputFile, String outputFile) {
        if (sortType == null) {
            System.out.println("No sorting type defined!");
            System.exit(-1);
        }
        if (dataType == null) {
            System.out.println("No data type defined!");
            System.exit(-2);
        }
        if (inputFile == null) {
            System.out.println("No valid input file argument!");
            System.exit(-3);
        }
        if (outputFile == null) {
            System.out.println("No valid output file argument!");
            System.exit(-4);
        }

        processRemainingArguments(argumentList);
    }

    private static void processRemainingArguments(List<String> arguments) {
        if (arguments.isEmpty()) {
            return;
        }
        for (String string : arguments) {
            System.out.println("\"" + string + "\"" + " is not a valid parameter. It will be skipped.");
        }
    }

    private static void executeSortStrategy(SortType sortType, DataCollector dataCollector) {
        dataCollector.processList();
        switch (sortType) {
            case byCount:
                dataCollector.sortCount();
                break;
            case natural:
            default:
                dataCollector.sortNatural();
        }
    }
}
