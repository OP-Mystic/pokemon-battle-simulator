import java.util.*;
public class Additionals {

    Pokemon[] extra;
    int cost[];
    Additionals()
    {
        extra=new Pokemon[9];
        cost=new int[9];
    }
    void fill()
    {
        Arrays.fill(cost,5000);
        extra[0] = new Pokemon("Toxtricity", 1354, 324, 262, 359, 262, 273, 17, 6,new String[]{"Throat Chop", "Wild Charge", "Sludge Wave", "Power Up Punch"}, new int[]{10, 10, 5, 10}, new long[]{80, 80, 95, 70}, new boolean[]{false, true, true, false}, new int[]{14, 17, 6, 5});
        extra[1] = new Pokemon("Staraptor", 1374, 372, 262, 218, 240, 328, 0, 8,new String[]{"Heat Wave", "Hurricane", "U Turn", "Air Slash"}, new int[]{5, 10, 10, 10}, new long[]{95, 110, 70, 75}, new boolean[]{true, true, false, true}, new int[]{1, 8, 10, 8});
        extra[2] = new Pokemon("Espeon", 1334, 251, 240, 394, 317, 350, 9, -1,new String[]{"Shadow Ball", "Tera Blast", "Power Gem", "Psychic"}, new int[]{10, 10, 10, 5}, new long[]{80, 80, 80, 90}, new boolean[]{true, true, true, true}, new int[]{12, 0, 11, 9});
        extra[3] = new Pokemon("Lucario", 1344, 350, 262, 361, 262, 306, 5, 15,new String[]{"Zen Headbutt", "Close Combat", "Flash Cannon", "Round"}, new int[]{10, 5, 10, 10}, new long[]{80, 120, 80, 60}, new boolean[]{false, false, true, true}, new int[]{9, 5, 15, 0});
        extra[4] = new Pokemon("Milotic", 1394, 240, 282, 328, 383, 287, 2, -1,new String[]{"Hydro Pump", "Ice Beam", "Dragon Pulse", "Bulldoze"}, new int[]{5, 10, 10, 10}, new long[]{110, 90, 85, 60}, new boolean[]{true, true, true, false}, new int[]{2, 4, 13, 7});
        extra[5] = new Pokemon("Decidueye", 1360, 344, 273, 328, 328, 262, 3, 12,new String[]{"Brave Bird", "Phantom Force", "Swift", "Sucker Punch"}, new int[]{5, 10, 10, 10}, new long[]{120, 90, 60, 70}, new boolean[]{false, false, true, false}, new int[]{8, 12, 0, 14});
        extra[6] = new Pokemon("Tyrantrum", 1368, 375, 370, 260, 238, 265, 11, 13,new String[]{"Iron Head", "Psychic Fangs", "Stone Edge", "Play Rough"}, new int[]{10, 10, 5, 10}, new long[]{80, 85, 100, 90}, new boolean[]{false, false, false, false}, new int[]{15, 9, 11, 16});
        extra[7] = new Pokemon("Infernape", 1356, 337, 265, 337, 265, 346, 1, 5,new String[]{"Flamethrower", "Close Combat", "Thunder Punch", "Iron Tail"}, new int[]{10, 5, 10, 10}, new long[]{90, 120, 70, 85}, new boolean[]{true, false, false, false}, new int[]{1, 5, 17, 15});
        extra[8] = new Pokemon("Florges", 1360, 251, 258, 355, 447, 273, 16, -1,new String[]{"Pollen Puff", "Dazzling Gleam", "Energy Ball", "Psychic"}, new int[]{10, 10, 10, 10}, new long[]{90, 80, 90, 90}, new boolean[]{true, true, true, true}, new int[]{10, 16, 3, 9});
    }
}
