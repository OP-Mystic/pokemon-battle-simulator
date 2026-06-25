import java.io.*;
import java.util.*;
public class gameArea {

    static final String MONEY_FILE = "money.txt";
    static final String BOUGHT_FILE = "bought.txt";
    static final int STARTING_MONEY = 5000;
    static final int EXTRA_COUNT = 9; // must match Additionals.extra.length

    // Creates money.txt with starting money if it doesn't already exist.
    static void ensureMoneyFile() throws IOException {
        File f = new File(MONEY_FILE);
        if (!f.exists()) {
            FileWriter fw = new FileWriter(f);
            fw.write(String.valueOf(STARTING_MONEY));
            fw.close();
            System.out.println("Created " + MONEY_FILE + " with starting money: " + STARTING_MONEY);
        }
        f.setWritable(false);
    }

    // Creates bought.txt with all extras marked unowned (0) if it doesn't already exist.
    static void ensureBoughtFile() throws IOException {
        File f = new File(BOUGHT_FILE);
        if (!f.exists()) {
            StringBuilder sb = new StringBuilder();
            for (int i = 0; i < EXTRA_COUNT; i++) {
                sb.append('0');
            }
            FileWriter fw = new FileWriter(f);
            fw.write(sb.toString());
            fw.close();
            System.out.println("Created " + BOUGHT_FILE + " with no extras owned");
        }
        f.setWritable(false);
    }

    static public boolean validity(String s)
    {
        char ch[]=s.trim().toCharArray();
        if(ch.length>9)
        {return false;}
        else if(ch.length==0)
        {return false;}
        for(int i=0;i<ch.length;i++)
        {
            if(!Character.isDigit(ch[i]))
            {
                return false;
            }
        }
        return true;

    }

    static void fileFill(int money) throws Exception
    {
        File f=new File(MONEY_FILE);
        f.setWritable(true);
        Scanner sc=new Scanner(f);
        int val= Integer.parseInt(sc.nextLine());
        FileWriter fw = new FileWriter(f);
        fw.write(Integer.toString(val+money));
        fw.close();
        f.setWritable(false);
        System.out.println("Game Saved Successfully. Total money:"+(val+money));
    }

    public static void main(String[] args) throws Exception {
            ensureMoneyFile();
            ensureBoughtFile();


            Scanner sc=new Scanner(System.in);
            while(true)
            {
                System.out.println("Choose 1 to play, 2 to shop and 0 to exit");
                String inp = sc.nextLine();
                while (!validity(inp) || Integer.parseInt(inp) > 2) {
                    System.out.println("Enter valid input");
                    inp = sc.nextLine();
                }
                switch (Integer.parseInt(inp)) {
                    case 1:
                        fileFill(SoftRun.play());

                        break;
                    case 2:
                        pokeShop.buy();
                        break;
                    case 0:
                        System.out.println("See you again!");
                        return;
                }
            }

            // Rest of the hub (play / buy menu) goes here.
    }

}
