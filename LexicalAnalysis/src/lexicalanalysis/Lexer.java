//LaSean Salmon
//Shubheksha Acharya

package lexicalanalysis;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;

public class Lexer {
    
    static private HashMap<String, String> reservedSymbols;
    static private char currentChar;
    static private String currentLexeme = "";
    static private BufferedReader buffer;
    static private FileReader reader;
    
    public Lexer() {
        reservedSymbols = new HashMap<>();
        initializeSymbols();
    }
    
    public static void Tokenize(String fileName) {
        
        try {
            reader = new FileReader(fileName);
            buffer = new BufferedReader(reader);
            
            nextChar();
            
            while(currentChar != (char)-1) {
                
                while(Character.isWhitespace(currentChar)) {
                    nextChar();
                }
                
                if(Character.isLetter(currentChar)) {
                    currentLexeme += currentChar;
                    nextChar();
                    
                    while(Character.isLetterOrDigit(currentChar)) {
                        currentLexeme += currentChar;
                        nextChar();
                    }
                    
                    if(findSymbol(currentLexeme)) {
                        currentLexeme = "";
                    }
                    else {
                        System.out.println("IDENT:" + currentLexeme);
                        currentLexeme = "";
                    }
                    
                }
                
                else if(Character.isDigit(currentChar)) {
                    currentLexeme += currentChar;
                    nextChar();
                    
                    while(Character.isDigit(currentChar)) {
                        currentLexeme += currentChar;
                        nextChar();
                    }
                    if(Character.isLetter(currentChar)) {
                        System.out.println("SYNTAX ERROR: INVALID IDENTIFIER NAME");
                        return;
                    }
                    
                    System.out.println("INT_LIT:" + currentLexeme);
                    currentLexeme = "";
                    
                }
                
                else {
                    currentLexeme += currentChar;
                    nextChar();
                    boolean newSymbol = false;
                    
                    while(!Character.isLetterOrDigit(currentChar) && !Character.isWhitespace(currentChar) && !newSymbol) {
                        
                        if(isSpecialSymbol(currentChar)) {
                            newSymbol = true;
                        } 
                        else {
                            currentLexeme += currentChar; 
                            nextChar();
                        }
                                            
                    }
                    
                    if(findSymbol(currentLexeme)) {
                        currentLexeme = "";
                    }
                    else {
                        System.out.println("SYNTAX ERROR: INVALID SYMBOL");
                        return;
                    }
                    
                }
                
            }
            
            System.out.println("EOF");
            
            try {
                reader.close();
                buffer.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
            
        } catch(IOException e){
            e.printStackTrace();
        }

    }
    
    static boolean findSymbol(String symbolKey) {
        
        if(reservedSymbols.get(symbolKey) != null) {
            System.out.println(reservedSymbols.get(symbolKey));
            return true;
        }
        else {
            return false;
        }
        
    }
    
    static int nextChar() {
        try {
            currentChar = (char)buffer.read();
        } catch (IOException e) {
            e.printStackTrace();
        }
        
        return currentChar;
    }
    
    static boolean isSpecialSymbol(char symbol){
        
        return Character.toString(symbol).equals("(") || Character.toString(symbol).equals(")") || Character.toString(symbol).equals("{") || Character.toString(symbol).equals("}") || Character.toString(symbol).equals(";");
        
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
        
    }

    
}
