import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;


public class Main {
        //Brugt til at tælle antal ord
    static Set<String> words = new HashSet<>();
    static HashMap<String, Integer> wordsCounter = new HashMap();

    public static void main(String[] args) {

        //Kalder metoden der læser filen
        fileReader();

        System.out.println(words);
        System.out.println(words.size());
        wordCounter();

        searchEngineCountWord();


    }

    public static void fileReader() {
        try {
            File myObj = new File("whale2.txt");
            Scanner myReader = new Scanner(myObj);
            while (myReader.hasNextLine()) {
                String data = myReader.nextLine();
                data = data.replaceAll("[-+.^:,?;'\"!\n&()0-9*]", "");
                data = data.toLowerCase();
                words.addAll(Arrays.asList(data.split(" ")));

            }
            myReader.close();
        } catch (FileNotFoundException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
        //Fjerner en streng den laver når der er to mellemrum efter hinanden
        words.remove("");

    }

//        public static void searchEngine() {
//
//            Scanner scanner = new Scanner(System.in);
//            System.out.println("Enter a word you think is in the text:");
//            String searchedWord = scanner.next();
//            if (words.contains(searchedWord)) {
//                System.out.println("Congratulations your word is in the text: " +searchedWord);
//            } else {
//                System.out.println("This word is not in the text, sorry :( uwu");
//            }
//        }

    public static void wordCounter() {
        try {
            File myObj = new File("whale2.txt");
            Scanner myReader = new Scanner(myObj);
            while (myReader.hasNextLine()) {
                String data = myReader.nextLine();
                data = data.replaceAll("[-+.^:,?;'\"!\n&()0-9*]", "");
                data = data.toLowerCase();
                for (String word:data.split(" ")) {
                    //Tjekker om ordene er der i forvejen, eller så opretter vi en ny
                    if(wordsCounter.containsKey(word)) {
                        wordsCounter.put(word, wordsCounter.get(word)+1);

                    } else{
                        wordsCounter.put(word, 1);
                    }


                }
            }
            myReader.close();
        } catch (FileNotFoundException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
        //Fjerner en streng den laver når der er to mellemrum efter hinanden
        wordsCounter.remove("");
        wordsCounter = sortByValue(wordsCounter);

        for (String words : wordsCounter.keySet()) {
            if (wordsCounter.get(words) >= 1000) {

                System.out.println(words + " " + wordsCounter.get(words));
            }
        }


    }

    public static void searchEngineCountWord () {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter a word you think is in the text and see how many times it appears:");
        String searchedWord = scanner.next();
        if (wordsCounter.containsKey(searchedWord)) {
            System.out.println("Congratulations " + searchedWord + " " + "appears in the text : "  + wordsCounter.get(searchedWord) + " times");
        } else {
            System.out.println("This word is not in the text, sorry :( uwu");
        }
    }

    public static HashMap<String, Integer> sortByValue(HashMap<String, Integer> hm)
    {
        // Create a list from elements of HashMap
        List<Map.Entry<String, Integer> > list =
                new LinkedList<Map.Entry<String, Integer> >(hm.entrySet());

        // Sort the list
        Collections.sort(list,new Comparator<Map.Entry<String, Integer> >() {
            public int compare(Map.Entry<String, Integer> o1,
                               Map.Entry<String, Integer> o2)
            {
                return (o2.getValue()).compareTo(o1.getValue());
            }
        });

        // put data from sorted list to hashmap
        HashMap<String, Integer> temp =new LinkedHashMap<String, Integer>();
        for (Map.Entry<String, Integer> aa : list) {
            temp.put(aa.getKey(), aa.getValue());
        }
        return temp;
    }







}