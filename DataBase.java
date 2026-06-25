import java.io.File;
import java.util.Arrays;
import java.util.Scanner;
public class DataBase {
    //0 Normal 1 Fire 2 Water 3 Grass 4 Ice 5 Fight 6 Poison 7 Ground 8 Flying 9 Psychic 10 Bug 11 Rock 12 Ghost 13 Dragon 14 Dark 15 Steel 16 Fairy 17 Electric    true special
    String bits;
    Item[] items;
    double[][] typeChart;

    DataBase()
    {
        bits="";
        items=new Item[7];
        typeChart=new double[18][18];
        }
    public int exCount() throws Exception
    {
        File f=new File("bought.txt");
        if(!f.exists())
        {return 0;}
        Scanner sc=new Scanner(f);
        bits=sc.nextLine();
        int c=0;
        for(int i=0;i<bits.length();i++)
        {
            if(bits.charAt(i)=='1')
            {
                c++;
            }
        }
        return c;
    }
    public Pokemon[] pokedex() throws Exception
    {
        Pokemon[] roster=new Pokemon[12+exCount()];
        roster[0]=new Pokemon("Charizard",1360,293,280,348,295,328,1,8,new String[]{"Fire Blast","Steel Wings","Dragon Pulse","Air Slash"},new int[]{5,10,10,10},new long[]{110,70,85,75},new boolean[]{true,false,true,true},new int[]{1,15,13,8});
        roster[1]=new Pokemon("Garchomp", 1420, 394, 317, 284, 295, 333,13,7 ,new String[]{"Dragon Claw", "Earth Power", "Brick Break", "Rock Slide"},new int[]{10,5,10,10},new long[]{85,90,75,75},new boolean[]{false,true,false,false},new int[]{13,7,5,11});
        roster[2]=new Pokemon("Ampharos", 1384, 273, 296, 361, 306, 229, 17, -1,new String[]{"Signal Beam", "Power Gem", "Brick Break", "Thunder Bolt"}, new int[]{10, 10, 10, 5}, new long[]{75, 80, 75, 90}, new boolean[]{true, true, false, true}, new int[]{10, 11, 5, 17});
        roster[3]=new Pokemon("Sceptile", 1344, 295, 251, 339, 295, 372, 3, -1,new String[]{"Energy Ball", "Thunder Punch", "Cross Poison", "Swift"}, new int[]{5, 10, 10, 10}, new long[]{90, 75, 70, 60}, new boolean[]{true, false, false, true}, new int[]{3, 17, 6, 0});
        roster[4]=new Pokemon("Greninja", 1348, 317, 256, 317, 256, 377, 2, 14,new String[]{"Hydro Pump", "Ice Punch", "Aerial Ace", "Dark Pulse"}, new int[]{5, 10, 10, 10}, new long[]{110, 75, 60, 80}, new boolean[]{true, false, false, true}, new int[]{2, 4, 8, 14});
        roster[5]=new Pokemon("Scizor", 1344, 394, 328, 229, 284, 251, 10, 15,new String[]{"Psycho Cut", "Bug Buzz", "Night Slash", "Steel Wings"}, new int[]{10, 5, 10, 10}, new long[]{70, 90, 70, 70}, new boolean[]{false, true, false, false}, new int[]{9, 10, 14, 15});
        roster[6]=new Pokemon("Glalie", 1364, 284, 284, 284, 284, 284, 4, -1,new String[]{"Blizzard", "Bulldoze", "Hex", "Iron Head"}, new int[]{5, 10, 10, 10}, new long[]{110, 60, 65, 80}, new boolean[]{true, false, true, false}, new int[]{4, 7, 12, 15});
        roster[7]=new Pokemon("Krookodile", 1394, 366, 284, 251, 262, 311, 7, 14,new String[]{"Earthquake", "Aqua Tail", "Dark Pulse", "Thunder Fang"}, new int[]{4, 10, 10, 10}, new long[]{100, 90, 80, 65}, new boolean[]{false, false, true, false}, new int[]{7, 2, 14, 17});
        roster[8]=new Pokemon("Gardevoir", 1340, 251, 251, 383, 361, 284, 9, 16,new String[]{"Expanding Force", "Misty Explosion", "Energy Ball", "Facade"}, new int[]{10, 5, 10, 10}, new long[]{80, 100, 90, 70}, new boolean[]{true, true, true, false}, new int[]{9, 16, 3, 0});
        roster[9]=new Pokemon("Snorlax", 1524, 350, 251, 251, 350, 174, 0, -1,new String[]{"Mega Kick", "Psychic", "Bite", "Seed Bomb"}, new int[]{5, 10, 10, 10}, new long[]{120, 90, 60, 80}, new boolean[]{false, true, false, false}, new int[]{0, 9, 14, 3});
        roster[10]=new Pokemon("Gengar", 1324, 251, 240, 394, 273, 350, 12, 6,new String[]{"Icy Wind", "Shadow Ball", "Sludge Wave", "Fire Punch"}, new int[]{10, 10, 5, 10}, new long[]{55, 80, 95, 75}, new boolean[]{true, true, true, false}, new int[]{4, 12, 6, 1});
        roster[11]=new Pokemon("Aurorus", 1450, 278, 267, 326, 311, 236, 4, 11,new String[]{"Psychic", "Meteor Beam", "Iron Head", "Ice Beam"}, new int[]{10, 5, 10, 10}, new long[]{90, 120, 80, 90}, new boolean[]{true, true, false, true}, new int[]{9, 11, 15, 4});
        Additionals add=new Additionals();
        add.fill();
        int idx=12;
        for(int i=0;i<add.extra.length;i++)
        {
            if(bits.charAt(i)=='1')
            {
                roster[idx]=add.extra[i];
                System.out.println(add.extra[i].Name);
                idx++;
            }
        }
        return roster;

    }
    public void ItemGrab()
    {
        items[0]=new Item("Full Restore",0);
        items[1]=new Item("Max Revive",1);
        items[2]=new Item("Full Heal",2);
        items[3]=new Item("Burn Heal",3);
        items[4]=new Item("Paralyze Heal",4);
        items[5]=new Item("Antidote",5);
        items[6]=new Item("Hyper Potion",6);
    }
    void setTypeChart()
    {
        for(int i=0;i<18;i++)
        {
            Arrays.fill(typeChart[i],1);
        }
        // Row 0: Normal
        typeChart[0][11] = 0.5; typeChart[0][12] = 0.0; typeChart[0][15] = 0.5;

    //  Row 1: Fire
        typeChart[1][1] = 0.5; typeChart[1][2] = 0.5; typeChart[1][3] = 2.0; typeChart[1][4] = 2.0; typeChart[1][10] = 2.0; typeChart[1][11] = 0.5; typeChart[1][13] = 0.5; typeChart[1][15] = 2.0;

    // Row 2: Water
        typeChart[2][1] = 2.0; typeChart[2][2] = 0.5; typeChart[2][3] = 0.5; typeChart[2][7] = 2.0; typeChart[2][11] = 2.0; typeChart[2][13] = 0.5;

    // Row 3: Grass
        typeChart[3][1] = 0.5; typeChart[3][2] = 2.0; typeChart[3][3] = 0.5; typeChart[3][6] = 0.5; typeChart[3][7] = 2.0; typeChart[3][8] = 0.5; typeChart[3][10] = 0.5; typeChart[3][11] = 2.0; typeChart[3][13] = 0.5; typeChart[3][15] = 0.5;

    // Row 4: Ice
        typeChart[4][1] = 0.5; typeChart[4][2] = 0.5; typeChart[4][3] = 2.0; typeChart[4][4] = 0.5; typeChart[4][7] = 2.0; typeChart[4][8] = 2.0; typeChart[4][13] = 2.0; typeChart[4][15] = 0.5;

    // Row 5: Fighting
        typeChart[5][0] = 2.0; typeChart[5][4] = 2.0; typeChart[5][6] = 0.5; typeChart[5][8] = 0.5; typeChart[5][9] = 0.5; typeChart[5][10] = 0.5; typeChart[5][11] = 2.0; typeChart[5][12] = 0.0; typeChart[5][14] = 2.0; typeChart[5][15] = 2.0; typeChart[5][16] = 0.5;

    // Row 6: Poison
        typeChart[6][3] = 2.0; typeChart[6][6] = 0.5; typeChart[6][7] = 0.5; typeChart[6][11] = 0.5; typeChart[6][12] = 0.5; typeChart[6][15] = 0.0; typeChart[6][16] = 2.0;

    // Row 7: Ground
        typeChart[7][1] = 2.0; typeChart[7][6] = 2.0; typeChart[7][8] = 0.0; typeChart[7][10] = 0.5; typeChart[7][11] = 2.0; typeChart[7][15] = 2.0; typeChart[7][17] = 2.0;

    // Row 8: Flying
        typeChart[8][3] = 2.0; typeChart[8][5] = 2.0; typeChart[8][10] = 2.0; typeChart[8][11] = 0.5; typeChart[8][15] = 0.5; typeChart[8][17] = 0.5;

    // Row 9: Psychic
        typeChart[9][5] = 2.0; typeChart[9][6] = 2.0; typeChart[9][9] = 0.5; typeChart[9][14] = 0.0; typeChart[9][15] = 0.5;

    // Row 10: Bug
        typeChart[10][1] = 0.5; typeChart[10][3] = 2.0; typeChart[10][5] = 0.5; typeChart[10][6] = 0.5; typeChart[10][8] = 0.5; typeChart[10][9] = 2.0; typeChart[10][12] = 0.5; typeChart[10][14] = 2.0; typeChart[10][15] = 0.5; typeChart[10][16] = 0.5;

    // Row 11: Rock
        typeChart[11][1] = 2.0; typeChart[11][4] = 2.0; typeChart[11][5] = 0.5; typeChart[11][7] = 0.5; typeChart[11][8] = 2.0; typeChart[11][10] = 2.0; typeChart[11][15] = 0.5;

    // Row 12: Ghost
        typeChart[12][0] = 0.0; typeChart[12][9] = 2.0; typeChart[12][12] = 2.0; typeChart[12][14] = 0.5;

    // Row 13: Dragon
        typeChart[13][13] = 2.0; typeChart[13][15] = 0.5; typeChart[13][16] = 0.0;

    // Row 14: Dark
        typeChart[14][5] = 0.5; typeChart[14][9] = 2.0; typeChart[14][12] = 2.0; typeChart[14][14] = 0.5; typeChart[14][16] = 0.5;

    // Row 15: Steel
        typeChart[15][1] = 0.5; typeChart[15][2] = 0.5; typeChart[15][4] = 2.0; typeChart[15][11] = 2.0; typeChart[15][15] = 0.5; typeChart[15][16] = 2.0; typeChart[15][17] = 0.5;

    // Row 16: Fairy
        typeChart[16][1] = 0.5; typeChart[16][5] = 2.0; typeChart[16][6] = 0.5; typeChart[16][13] = 2.0; typeChart[16][14] = 2.0; typeChart[16][15] = 0.5;

    // Row 17: Electric
        typeChart[17][2] = 2.0; typeChart[17][3] = 0.5; typeChart[17][7] = 0.0; typeChart[17][8] = 2.0; typeChart[17][17] = 0.5;

    }
    String[] Typing()
    {
      String[]  Types = {"Normal", "Fire", "Water", "Grass", "Ice", "Fight", "Poison", "Ground", "Flying", "Psychic", "Bug", "Rock", "Ghost", "Dragon", "Dark", "Steel", "Fairy", "Electric"};
        return Types;
    }
}
