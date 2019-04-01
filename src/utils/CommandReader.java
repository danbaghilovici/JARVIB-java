package utils;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class CommandReader extends BaseReader{
    private ArrayList<String> commandLines;
    private Map<ArrayList<String>,Integer> readerCommandsList;
    private Map<ArrayList<String>,Integer> readerMacrosList;

    public CommandReader(String fileName) {
        super(fileName);
        commandLines=super.read();
    }




}

