//LaSean Salmon
//Shubheksha Acharya

package lexicalanalysis;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;

public class Lexer {
    
    static HashMap<String, String> reservedSymbols;
    
    public Lexer() {
        reservedSymbols = new HashMap<>();
        initializeSymbols();
    }
    
    public static void Tokenize(String fileName) {
        
        try {
            FileReader reader = new FileReader(fileName);
            BufferedReader buffer = new BufferedReader(reader);
            
            try{
                
                int currentChar;
                
                while((currentChar = buffer.read()) != -1) {
                    System.out.println((char)currentChar);
                }
                
            }
            //catch error if file cannot be read
            catch(IOException e) {
                e.printStackTrace();
            }
            
            try {
                reader.close();
                buffer.close();
            }
            //catch error if file cannot be closed
            catch (IOException e) {
                e.printStackTrace();
            }
            
        }
        //catch error if file cannot be opened
        catch(IOException e){
            e.printStackTrace();
        }

    }
    
    static boolean findSymbol(String symbolKey) {
        
        if(reservedSymbols.get(symbolKey) != null) {
            System.out.println(reservedSymbols.get(symbolKey));
            return true;
        }
        else {
            System.out.println("SYNTAX ERROR: INVALID IDENTIFIER NAME");
            return false;
        }
        
    }
    
    static void initializeSymbols() {
        reservedSymbols.put("if", "IF");
        reservedSymbols.put("for", "FOR");
        reservedSymbols.put("while", "WHILE");
        reservedSymbols.put("function", "FUNCTION");
        reservedSymbols.put("return", "RETURN");
        reservedSymbols.put("int", "INT");
        reservedSymbols.put("else", "ELSE");
        reservedSymbols.put("do", "DO");
        reservedSymbols.put("break", "BREAK");
        reservedSymbols.put("end", "END");
        
        reservedSymbols.put("=", "ASSIGN");
        reservedSymbols.put("+", "ADD");
        reservedSymbols.put("-", "SUB");
        reservedSymbols.put("*", "MUL");
        reservedSymbols.put("/", "DIV");
        reservedSymbols.put("%", "MOD");
        reservedSymbols.put(">", "GT");
        reservedSymbols.put("<", "LT");
        reservedSymbols.put(">=", "GE");
        reservedSymbols.put("<=", "LE");
        reservedSymbols.put("++", "INC");
        reservedSymbols.put("(", "LP");
        reservedSymbols.put(")", "RP");
        reservedSymbols.put("{", "LB");
        reservedSymbols.put("}", "RB");
        reservedSymbols.put("|", "OR");
        reservedSymbols.put("&", "AND");
        reservedSymbols.put("==", "EE");
        reservedSymbols.put("!", "NEG");
        reservedSymbols.put(",", "COMMA");
        reservedSymbols.put(";", "SEMI");
        
        return;
    }

    
}
