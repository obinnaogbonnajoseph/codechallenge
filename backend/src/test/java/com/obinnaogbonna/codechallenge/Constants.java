package com.obinnaogbonna.codechallenge;

class Constants {

    static String getJavaStarterCode() {
        return """
                    public class MyImpl {
                          public String mySolution(String val) {
                
                              return "Hello World";
                          }
                
                          public static void main(String[] args) {
                            String val = new MyImpl().mySolution("hello world");
                            System.out.println(val);
                          }
                    }
                """;
    }

    static String getJavaScriptStarterCode() {
        return """
                      function mySolution(val) {
                          return "Hello World";
                      }
                      function output() {
                        console.log(mySolution("hello world"));
                      }
                      output();
                """;
    }
}
