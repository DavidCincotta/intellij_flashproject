import javax.net.ssl.HttpsURLConnection;
import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Stack;

public class TestingMain {
    private static final String USER_AGENT = "Mozilla/5.0";
    public static String key = "AIzaSyDnX0RR5nivgnSZmBtI9o5gM0aZ2qVkHSI";
    public static HashMap<String, Double> variables = new HashMap();

    public static void main(String[] args) throws Exception {
        /*System.out.println(fibonacci(4));
        System.out.println(stripString("You can't always get what you want, buddy!!"));
        printStars(3);
        System.out.println(createRandWord());
        //System.out.println(10000 % 10.1);
        generateEmails();
        Stack stack = new Stack();
        System.out.println("");
        stack.add("aa");
        stack.add(11);
        System.out.println(stack.peek());
        System.out.println(stack);
        */

        //System.out.println(reversePolishNotation());
        //System.out.println(getElevation(4, 4));
        //sendPost();
        //System.out.println(getElevation(42.377853, -71.420689));
        while(true) {
            System.out.println(reversePolishNotation());
        }

    }

    public static double getElevation(double lat, double lng) throws Exception {
        //InputStreamWrapper inputStreamWrapper = new InputStreamWrapper();
        String urlToRead = "https://maps.googleapis.com/maps/api/elevation/json?locations=" + lat + "," + lng + "&key=AIzaSyDnX0RR5nivgnSZmBtI9o5gM0aZ2qVkHSI";

        StringBuilder result = new StringBuilder();
        URL url = new URL(urlToRead);
        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
        conn.setRequestMethod("GET");
        BufferedReader rd = new BufferedReader(new InputStreamReader(conn.getInputStream()));
        String line;
        while ((line = rd.readLine()) != null) {
            result.append(line);
        }
        rd.close();
        String json = result.toString();
        System.out.println(json);
        String elevationString = json.substring(json.indexOf("elevation\" : ") + 13, json.indexOf(","));
        double elevation = Double.parseDouble(elevationString);
        return elevation;

    }

    static void sendPost() throws Exception {

        String url = "https://www.googleapis.com/geolocation/v1/geolocate?key=AIzaSyDnX0RR5nivgnSZmBtI9o5gM0aZ2qVkHSI";
        URL obj = new URL(url);
        HttpsURLConnection con = (HttpsURLConnection) obj.openConnection();

        //add reuqest header
        con.setRequestMethod("POST");
        con.setRequestProperty("User-Agent", USER_AGENT);
        con.setRequestProperty("Accept-Language", "en-US,en;q=0.5");

        String urlParameters = "";

        // Send post request
        con.setDoOutput(true);
        DataOutputStream wr = new DataOutputStream(con.getOutputStream());
        wr.writeBytes(urlParameters);
        wr.flush();
        wr.close();

        int responseCode = con.getResponseCode();
        System.out.println("\nSending 'POST' request to URL : " + url);
        System.out.println("Post parameters : " + urlParameters);
        System.out.println("Response Code : " + responseCode);

        BufferedReader in = new BufferedReader(
                new InputStreamReader(con.getInputStream()));
        String inputLine;
        StringBuffer response = new StringBuffer();

        while ((inputLine = in.readLine()) != null) {
            response.append(inputLine);
        }
        in.close();

        //print result
        System.out.println(response.toString());

    }

    public static Object reversePolishNotation() {
        Stack stack = new Stack();
        for (String a :
                readLine("Reverse Polish Notation: ").split(" ")) {
            try {
                if (a.equals("true")) {
                    stack.push(true); //Max and Min values seem a better representation of booleans
                } else if (a.equals("false")) {     //as the actual values are never reached in practical code
                    stack.push(false);
                } else {

                    double g = Double.parseDouble(a);
                    stack.push(g);
                }
            } catch (NumberFormatException e) {
                System.out.println(stack);
                Object object1, object2;
                switch (a) {
                    case "+":
                        object1 = stack.pop();
                        object2 = stack.pop();
                        if (variables.containsKey(object1.toString())){
                            object1 = variables.get(object1.toString());
                        } else if (variables.containsKey(object2.toString())){
                            object2 = variables.get(object2.toString());
                        }
                        if (object1.equals(true) || object1.equals(false) || object2.equals(true) || object2.equals(false))
                            System.exit(1);
                        stack.push(Double.parseDouble(object1.toString()) + Double.parseDouble(object2.toString()));
                        break;
                    case "-":
                        object1 = stack.pop();
                        object2 = stack.pop();
                        if (variables.containsKey(object1.toString())){
                            object1 = variables.get(object1.toString());
                        } else if (variables.containsKey(object2.toString())){
                            object2 = variables.get(object2.toString());
                        }
                        if (object1.equals(true) || object1.equals(false) || object2.equals(true) || object2.equals(false))
                            System.exit(1);
                        stack.push(Double.parseDouble(object2.toString()) - Double.parseDouble(object1.toString()));
                        break;
                    case "--":
                        object1 = stack.pop();
                        if (variables.containsKey(object1.toString())){
                            object1 = variables.get(object1.toString());
                        }
                        if (object1.equals(true) || object1.equals(false)) System.exit(1);
                        stack.push(Double.parseDouble(object1.toString()) * -1);
                        break;
                    case "*":
                        object1 = stack.pop();
                        object2 = stack.pop();
                        if (variables.containsKey(object1.toString())){
                            object1 = variables.get(object1.toString());
                        } else if (variables.containsKey(object2.toString())){
                            object2 = variables.get(object2.toString());
                        }
                        if (object1.equals(true) || object1.equals(false) || object2.equals(true) || object2.equals(false))
                            System.exit(1);
                        stack.push(Double.parseDouble(object2.toString()) * Double.parseDouble(object1.toString()));
                        break;
                    case "/":
                        object1 = stack.pop();
                        object2 = stack.pop();
                        if (variables.containsKey(object1.toString())){
                            object1 = variables.get(object1.toString());
                        } else if (variables.containsKey(object2.toString())){
                            object2 = variables.get(object2.toString());
                        }
                        if (object1.equals(true) || object1.equals(false) || object2.equals(true) || object2.equals(false))
                            System.exit(1);
                        stack.push(Double.parseDouble(object2.toString()) / Double.parseDouble(object1.toString()));
                        break;
                    case "^":
                        object1 = stack.pop();
                        object2 = stack.pop();
                        if (variables.containsKey(object1.toString())){
                            object1 = variables.get(object1.toString());
                        } else if (variables.containsKey(object2.toString())){
                            object2 = variables.get(object2.toString());
                        }
                        if (object1.equals(true) || object1.equals(false) || object2.equals(true) || object2.equals(false))
                            System.exit(1);
                        stack.push(Math.pow(Double.parseDouble(object2.toString()), Double.parseDouble(object1.toString())));
                        break;
                    case "!":
                        object1 = stack.pop();
                        object2 = 0;
                        if (variables.containsKey(object1.toString())){
                            object1 = variables.get(object1.toString());
                        }
                        if (object1.equals(true) || object1.equals(false) || object2.equals(true) || object2.equals(false))
                            System.exit(1);
                        if (Double.parseDouble(object1.toString()) % 1 != 0 && Double.parseDouble(object1.toString()) < 0) {
                            break;
                        }
                        double temp = 1;
                        for (int i = 1; i <= Double.parseDouble(object1.toString()); i++) {
                            temp = temp * i;
                        }
                        stack.push(temp);
                        break;
                    case "%":
                        object1 = stack.pop();
                        object2 = stack.pop();
                        if (variables.containsKey(object1.toString())){
                            object1 = variables.get(object1.toString());
                        }
                        if (object1.equals(true) || object1.equals(false) || object2.equals(true) || object2.equals(false))
                            System.exit(1);
                        if (Double.parseDouble(object1.toString()) % 1 != 0 && Double.parseDouble(object1.toString()) < 0) {
                            break;
                        }
                        stack.push(Double.parseDouble(object2.toString()) % Double.parseDouble(object1.toString()));
                        break;
                    case "<":
                        object1 = stack.pop();
                        object2 = stack.pop();
                        if (variables.containsKey(object1.toString())){
                            object1 = variables.get(object1.toString());
                        } else if (variables.containsKey(object2.toString())){
                            object2 = variables.get(object2.toString());
                        }
                        if (object1.equals(true) || object1.equals(false) || object2.equals(true) || object2.equals(false))
                            System.exit(1);
                        if (Double.parseDouble(object1.toString()) > Double.parseDouble(object2.toString())) {
                            stack.push(true);
                            break;
                        } else {
                            stack.push(false);
                            break;
                        }
                    case ">":
                        object1 = stack.pop();
                        object2 = stack.pop();
                        if (variables.containsKey(object1.toString())){
                            object1 = variables.get(object1.toString());
                        } else if (variables.containsKey(object2.toString())){
                            object2 = variables.get(object2.toString());
                        }
                        if (object1.equals(true) || object1.equals(false) || object2.equals(true) || object2.equals(false))
                            System.exit(1);
                        if (Double.parseDouble(object1.toString()) < Double.parseDouble(object2.toString())) {
                            stack.push(true);
                            break;
                        } else {
                            stack.push(false);
                            break;
                        }
                    case "<=":
                        object1 = stack.pop();
                        object2 = stack.pop();
                        if (variables.containsKey(object1.toString())){
                            object1 = variables.get(object1.toString());
                        } else if (variables.containsKey(object2.toString())){
                            object2 = variables.get(object2.toString());
                        }
                        if (object1.equals(true) || object1.equals(false) || object2.equals(true) || object2.equals(false))
                            System.exit(1);
                        if (Double.parseDouble(object1.toString()) >= Double.parseDouble(object2.toString())) {
                            stack.push(true);
                            break;
                        } else {
                            stack.push(false);
                            break;
                        }
                    case ">=":
                        object1 = stack.pop();
                        object2 = stack.pop();
                        if (variables.containsKey(object1.toString())){
                            object1 = variables.get(object1.toString());
                        } else if (variables.containsKey(object2.toString())){
                            object2 = variables.get(object2.toString());
                        }
                        if (object1.equals(true) || object1.equals(false) || object2.equals(true) || object2.equals(false))
                            System.exit(1);
                        if (Double.parseDouble(object1.toString()) <= Double.parseDouble(object2.toString())) {
                            stack.push(true);
                            break;
                        } else {
                            stack.push(false);
                            break;
                        }
                    case "==":
                        object1 = stack.pop();
                        object2 = stack.pop();
                        if (variables.containsKey(object1.toString())){
                            object1 = variables.get(object1.toString());
                        } else if (variables.containsKey(object2.toString())){
                            object2 = variables.get(object2.toString());
                        }
                        if (object1 == object2) {
                            stack.push(true);
                            break;
                        } else {
                            stack.push(false);
                            break;
                        }
                    case "||":
                        object1 = stack.pop();
                        object2 = stack.pop();
                        if (object1.equals(true) || object2.equals(true)) {
                            stack.push(true);
                            break;
                        } else {
                            stack.push(false);
                            break;
                        }
                    case "&&":
                        object1 = stack.pop();
                        object2 = stack.pop();
                        if (Boolean.parseBoolean(object1.toString()) && Boolean.parseBoolean(object2.toString())) {
                            stack.push(true);
                            break;
                        } else {
                            stack.push(false);
                            break;
                        }
                    case"sin":
                        object1 = stack.pop();
                        if (variables.containsKey(object1.toString())){
                            object1 = variables.get(object1.toString());
                        }
                        if (object1.equals(true) || object1.equals(false) )
                            System.exit(1);
                        stack.push(Math.sin(Double.parseDouble(object1.toString())));
                        break;
                    case"cos":
                        object1 = stack.pop();
                        if (variables.containsKey(object1.toString())){
                            object1 = variables.get(object1.toString());
                        }
                        if (object1.equals(true) || object1.equals(false) )
                            System.exit(1);
                        stack.push(Math.cos(Double.parseDouble(object1.toString())));
                        break;
                    case"tan":
                        object1 = stack.pop();
                        if (variables.containsKey(object1.toString())){
                            object1 = variables.get(object1.toString());
                        }
                        if (object1.equals(true) || object1.equals(false) )
                            System.exit(1);
                        stack.push(Math.tan(Double.parseDouble(object1.toString())));
                        break;
                    case "=":
                        object1 = stack.pop();
                        object2 = stack.pop();
                        variables.remove(object2.toString());
                        variables.put(object2.toString(), Double.parseDouble(object1.toString()));
                        stack.push(variables.get(object2.toString()));
                        break;
                    default:
                        System.out.println(a + " is variable");
                        if (!variables.containsKey(a)) {
                            variables.put(a, null);
                            stack.push(a);
                        } else if (variables.containsKey(a)){
                            stack.push(variables.get(a));
                        }
                        break;
                }
            }
        }
        System.out.println(variables);
        if (variables.containsKey(stack.peek())){
            return variables.get(stack.peek());
        }
        return stack.peek();
    }

    public static String readLine(String prompt) {
        String line = null;
        Console c = System.console();
        if (c != null) {
            line = c.readLine(prompt);
        } else {
            System.out.print(prompt);
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
            try {
                line = bufferedReader.readLine();
            } catch (IOException e) {
                //Ignore
            }
        }
        return line;
    }

    public static void generateEmails() {
        System.out.print("{");
        for (int i = 0; i < 10; i++) {
            System.out.print("\"");
            for (int j = 0; j < 1 + (int) (Math.random() * 15); j++) {
                System.out.print("a");
            }
            System.out.print("@");
            for (int j = 0; j < 1 + (int) (Math.random() * 15); j++) {
                System.out.print("a");
            }
            System.out.print(".com");
            System.out.print("\",");
        }
        System.out.print("}");
    }

    //random coding tasks
    public static int fibonacci(int n) {
        ArrayList<Integer> a = new ArrayList<>();
        a.add(1);
        a.add(1);
        for (int i = 0; i < 1000; i++) {
            a.add(a.get(i) + a.get(i + 1));
        }
        return a.get(n - 1);
    }

    public static String stripString(String a) {
        return a.replaceAll("!", "").replaceAll(",", "").replaceAll("'", "").toLowerCase();
    }

    public static void printStars(int maxWidth) {
        int numberInRow = 1;
        for (int i = 0; i < maxWidth; i++) {
            for (int j = 0; j < numberInRow; j++) {
                System.out.print("*");
            }
            numberInRow++;
            System.out.println("");
        }
        numberInRow--;
        numberInRow--;
        for (int i = 0; i < maxWidth; i++) {
            for (int j = 0; j < numberInRow; j++) {
                System.out.print("*");
            }
            numberInRow--;
            System.out.println("");
        }
    }

    public static String createRandWord() {
        String[] wordArray = {"", "", "", "", ""};
        String[] vowels = {"a", "e", "o", "i", "u", "y"};
        String[] constants = {"q", "w", "r", "t", "p", "s", "d", "f", "g", "h", "j", "k", "l", "z", "x", "c", "v", "b", "n", "m"};
        wordArray[0] = constants[(int) (Math.random() * constants.length)];
        wordArray[1] = vowels[(int) (Math.random() * vowels.length)];
        wordArray[2] = constants[(int) (Math.random() * constants.length)];
        wordArray[3] = vowels[(int) (Math.random() * vowels.length)];
        wordArray[4] = constants[(int) (Math.random() * constants.length)];
        String a = "";
        for (String b :
                wordArray) {
            a += b;
        }
        return a;
    }
}