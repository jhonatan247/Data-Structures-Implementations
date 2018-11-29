package huffman;

import java.util.*;
import java.io.*;

public class Huffman {

    private String inputFile;
    private String outputFile;
    private String binaryMessage;
    private Heap frequencyQueue;

    public Huffman(String inputFile, String outputFile) {
        this.inputFile = inputFile;
        this.outputFile = outputFile;
    }

    private String getFileText() throws Exception {
        StringBuilder text = new StringBuilder();

        try (FileReader in = new FileReader(inputFile)) {
            BufferedReader br = new BufferedReader(in);

            String line = br.readLine();
            boolean isFirst = true;
            while (line != null) {
                if(!isFirst) text.append(System.getProperty("line.separator"));
                else isFirst= false;
                text.append(line);
                line = br.readLine();
            }
        }
        return text.toString();
    }

    private TreeMap<String, Character> getFileMap() throws Exception {
        StringBuilder text = new StringBuilder();

        TreeMap<String, Character> values = new TreeMap();
        
        try (FileReader in = new FileReader(inputFile)) {
            BufferedReader br = new BufferedReader(in);
            int count = Integer.parseInt(br.readLine());
            StringTokenizer line;
            Character character;
            String code;
            for (int i = 0; i < count; i++) {
                line = new StringTokenizer(br.readLine());
                character = (char) Integer.parseInt(line.nextToken());
                code = line.nextToken();
                values.put(code, character);
            }
            binaryMessage = br.readLine();

        }
        return values;
    }

    private void setFileText(StringBuilder text) throws Exception {
        try (PrintStream salida = new PrintStream(new File(outputFile))) {
            salida.print(text);
        }
    }

    public void compress() throws Exception {
        String inputText = getFileText();
        if (inputText.equals("")) {
            setFileText(new StringBuilder());
            return;
        }

        frequencyQueue = new Heap(inputText);
        while (frequencyQueue.size() > 1) {
            frequencyQueue.add( new BinaryTree(frequencyQueue.removeMax(), frequencyQueue.removeMax()) );
        }
        
        BinaryTree finalTree = frequencyQueue.getMax();
        TreeMap compressCode = finalTree.getBinatyRepresentation();

        StringBuilder output = new StringBuilder();
        
        output.append(compressCode.size());
        output.append(System.getProperty("line.separator"));
        output.append(finalTree.toString());
        for (int i = 0; i < inputText.length(); i++) {
            output.append( compressCode.get(inputText.charAt(i)) );
        }
        setFileText(output);
        System.out.println("Text compressed correctly in "+outputFile);
    }

    public void uncompress() throws Exception {
        TreeMap<String,Character> compressCode = getFileMap();
        StringBuilder currentKey = new StringBuilder();
        Character currentChar; 
        StringBuilder uncompressText = new StringBuilder();
        for(int i = 0; i<binaryMessage.length(); i++ ){
            currentKey.append(binaryMessage.charAt(i));
            currentChar = compressCode.get(currentKey.toString());
            if(currentChar!= null){
                uncompressText.append(currentChar);
                currentKey = new StringBuilder();
            }
        }
        setFileText(uncompressText);
        System.out.println("Text uncompressed correctly in "+outputFile);
    }

    public static void main(String[] args) throws Exception {
        if (args.length == 3) {
            if (args[0].equals("-c")) {
                new Huffman(args[1], args[2]).compress();
            } else if (args[0].equals("-u")) {
                new Huffman(args[1], args[2]).uncompress();
            } else {

            }
        } else {
            System.out.println("Compress: -c <input file> <output file>");
            System.out.println("Uncompress: -u <input file> <output file>");
        }
    }

}
