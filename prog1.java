//------------------------------------------------------------------------
//Michael Chunko
//12/15/15
//A program that reads in 3 octal numbers, 3 binary numbers,
//or a set of 3 strings representing CHMOD permissions and displays it's
//equivalent representation in the other 3 forms
//------------------------------------------------------------------------

import java.io.*;
import java.nio.file.*;

public class prog1 {

    public static void main(String[] args) {
        //Variable declaration
        String input = "", binary = "", octal = "", permissions = "", binaryLine = "", octalLine = "", permissionsLine = "";

        try {
            //Reads in each line
            for (String line : Files.readAllLines(Paths.get("E:/ASCLinput.txt"))) {
                binaryLine = "";
                octalLine = "";
                permissionsLine = "";

                //Splits the input wherever there is a ','
                for (String part : line.split(",")) {
                    binary = "";
                    octal = "";
                    permissions = "";
                    input = "";

                    //Removes any spaces from the input
                    for (int i = 0; i < part.length(); i++) {
                        if (part.charAt(i) != ' ')
                            input += part.charAt(i);
                    }

                    //Begins testings for each possible octal, binary, and permissions value
                    if (input.equals("0")) {
                        binary += "000";
                        permissions += "---";
                    } else {
                        if (input.equals("1")) {
                            binary += "001";
                            permissions += "--x";
                        } else {
                            if (input.equals("2")) {
                                binary += "010";
                                permissions += "-w-";
                            } else {
                                if (input.equals("3")) {
                                    binary += "011";
                                    permissions += "-wx";
                                } else {
                                    if (input.equals("4")) {
                                        binary += "100";
                                        permissions += "r--";
                                    } else {
                                        if (input.equals("5")) {
                                            binary += "101";
                                            permissions += "r-x";
                                        } else {
                                            if (input.equals("6")) {
                                                binary += "110";
                                                permissions += "rw-";
                                            } else {
                                                if (input.equals("7")) {
                                                    binary += "111";
                                                    permissions += "rwx";
                                                } else {
                                                    if (input.equals("000")) {
                                                        octal += "0";
                                                        permissions += "---";
                                                    } else {
                                                        if (input.equals("001")) {
                                                            octal += "1";
                                                            permissions += "--x";
                                                        } else {
                                                            if (input.equals("010")) {
                                                                octal += "2";
                                                                permissions += "-w-";
                                                            } else {
                                                                if (input.equals("011")) {
                                                                    octal += "3";
                                                                    permissions += "-wx";
                                                                } else {
                                                                    if (input.equals("100")) {
                                                                        octal += "4";
                                                                        permissions += "r--";
                                                                    } else {
                                                                        if (input.equals("101")) {
                                                                            octal += "5";
                                                                            permissions += "r-x";
                                                                        } else {
                                                                            if (input.equals("110")) {
                                                                                octal += "6";
                                                                                permissions += "rw-";
                                                                            } else {
                                                                                if (input.equals("111")) {
                                                                                    octal += "7";
                                                                                    permissions += "rwx";
                                                                                } else {
                                                                                    if (input.equals("---")) {
                                                                                        octal += "0";
                                                                                        binary += "000";
                                                                                    } else {
                                                                                        if (input.equals("--x")) {
                                                                                            octal += "1";
                                                                                            binary += "001";
                                                                                        } else {
                                                                                            if (input.equals("-w-")) {
                                                                                                octal += "2";
                                                                                                binary += "010";
                                                                                            } else {
                                                                                                if (input.equals("-wx")) {
                                                                                                    octal += "3";
                                                                                                    binary += "011";
                                                                                                } else {
                                                                                                    if (input.equals("r--")) {
                                                                                                        octal += "4";
                                                                                                        binary += "100";
                                                                                                    } else {
                                                                                                        if (input.equals("r-x")) {
                                                                                                            octal += "5";
                                                                                                            binary += "101";
                                                                                                        } else {
                                                                                                            if (input.equals("rw-")) {
                                                                                                                octal += "6";
                                                                                                                binary += "110";
                                                                                                            } else {
                                                                                                                if (input.equals("rwx")) {
                                                                                                                    octal += "7";
                                                                                                                    binary += "111";
                                                                                                                }
                                                                                                            }
                                                                                                        }
                                                                                                    }
                                                                                                }
                                                                                            }
                                                                                        }
                                                                                    } //Ends the testing for permissions values
                                                                                }
                                                                            }
                                                                        }
                                                                    }
                                                                }
                                                            }
                                                        }
                                                    } //Ends the testing for binary values
                                                }
                                            }
                                        }
                                    }
                                }
                            }
                        }
                    } //Ends the testing for octal values

                    binaryLine += binary + " ";
                    octalLine += octal;
                    permissionsLine += permissions + " ";

                }  //Ends the current input

                //Prints out the conversions for the current line of input
                if (binary.equals(""))
                    System.out.println(octalLine + " and " + permissionsLine);
                else {
                    if (octal.equals(""))
                        System.out.println(binaryLine + "and " + permissionsLine);
                    else
                        System.out.println(octalLine + " and " + binaryLine);
                }
            }  //Ends the input
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
