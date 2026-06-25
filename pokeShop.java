import java.io.*;
import java.util.*;
public class pokeShop {

    static void buy() throws Exception
    {
        File f=new File("bought.txt");
        if(!f.exists())
        {
            System.out.println("File not present");
            return;
        }

        File f1=new File("money.txt");
        if(!f1.exists())
        {
            System.out.println("File not present");
            return;
        }
        f.setWritable(true);
        f1.setWritable(true);
        System.out.println("Welcome to the shop. Here you can buy new extra pokemons");
        Scanner sc=new Scanner(f);
        String s=sc.nextLine();
        char ch[]=s.toCharArray();
        sc=new Scanner(f1);
        int money=Integer.parseInt(sc.nextLine());
        System.out.println("Money= "+money);
        Additionals add=new Additionals();
        add.fill();
        System.out.println("Pokemons not yet bought");
        for(int i=0;i<add.extra.length;i++)
        {
            if(ch[i]=='0')
            { System.out.println(i+" "+add.extra[i].Name+"   Cost:"+add.cost[i]);

            }
        }
        sc=new Scanner(System.in);
        while(true)
        {
            System.out.println("Pick the index among the displayed ones to buy that pokemon to be added to the roster.Or any other number to exit");
            String inp = sc.nextLine();
            while (!gameArea.validity(inp)) {
                System.out.println("Enter a whole number");
                inp = sc.nextLine();
            }
            int val = Integer.parseInt(inp);
            if (val >= add.extra.length)
            {
                FileWriter fw1 = new FileWriter(f1);
                fw1.write(Integer.toString(money));
                f1.setWritable(false);
                fw1.close();
                FileWriter fw = new FileWriter(f);
                fw.write(String.valueOf(ch));
                fw.close();
                f.setWritable(false);
                return;
            }
            if (ch[val] == '1') {
                System.out.println("Already bought");
                continue;
            }
            if (money < add.cost[val]) {
                System.out.println("Not Enough Money");
                continue;
            }
            System.out.println("Are you sure with " + add.extra[val].Name + " for " + add.cost[val]);
            System.out.println("0 for No and 1 for Yes");
            inp = sc.nextLine();
            while (!gameArea.validity(inp) || Integer.parseInt(inp) > 1) {
                System.out.println("Enter a whole number");
                inp = sc.nextLine();
            }
            switch (Integer.parseInt(inp)) {
                case 0:
                    continue;
                case 1:
                    money -= add.cost[val];
                    System.out.println(add.extra[val].Name + " sold");
                    ch[val] = '1';
                    System.out.println("Money left= " + money);
                    break;
            }
        }

    }

}
