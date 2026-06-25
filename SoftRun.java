import java.util.*;
public class SoftRun {

    static String effect;

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

    static void Selection(int team[][], Pokemon[] Roster)
    {
        Scanner sc=new Scanner(System.in);
        HashSet<Integer> hs=new HashSet<>();
        int rnd=(int)(Math.random()*1000000+1);
        for(int i=0;i<Roster.length;i++)
        {
            hs.add((i+1)*rnd);
        }
        System.out.println("Select Your 3 Pokemons by their index, The Opponent will have Random Pokemon from the Leftovers");
        int idx=0;
        while(idx<3)
        {
            String inp=sc.nextLine();
            if(validity(inp)&&Integer.parseInt(inp)<Roster.length)
            {
                int val=Integer.parseInt(inp);
                if(!hs.contains((val+1)*rnd))
                {System.out.println("Already Selected, Choose Again");
                    continue;}
                team[0][idx]=val;
                System.out.println(Roster[val].Name+" selected\nTeam:"+(idx+1)+"/3");
                hs.remove((val+1)*rnd);
                idx++;
            }
            else
            {
                System.out.println("Enter Valid Input");
            }
        }
        idx=0;
        Iterator<Integer> iter=hs.iterator();
        while(idx<3)
        {
            team[1][idx]=(iter.next()/rnd-1);
            idx++;
        }
    }


    static void showMove(Pokemon poke,String[] Type)
    {
        for(int i=0;i<4;i++)
        {
            System.out.println((i+1)+". "+poke.Moveset[i]+" Type:"+Type[poke.AttType[i]]+" Special:"+poke.PhySpl[i]+" PP:"+poke.PP[i]+" Power:"+poke.Power[i]);
        }
    }


    static boolean itemWork(Pokemon[] Roster,Pokemon[] backUp,int team[][],Item items[]) {
        Scanner sc = new Scanner(System.in);
        System.out.println("Here are the items available to you. Choose the index of the item and then the Pokemon to use it on.Or choose any other number to discard");
        for (int i = 0; i < items.length; i++) {
            if (items[i].Have)
                System.out.println(items[i].Index + " : " + items[i].Name);
        }
        String inp=sc.nextLine();
        while(!validity(inp))
        {
            System.out.println("Not a number");
            inp=sc.nextLine();
        }
        int val= Integer.parseInt(inp);
        if(val>=items.length)
        {
            return false;
        }
        else if(!items[val].Have)
        {
            System.out.println("Item already used");
            return false;
        }

        System.out.println("Select Pokemon");
        for(int i=0;i<3;i++)
        {
            System.out.println(i+" "+Roster[team[0][i]].Name);
        }
        inp = sc.nextLine();
        while (!validity(inp) || Integer.parseInt(inp) > 2) {
            System.out.println("Give suitable input");
            inp = sc.nextLine();
        }
        int pidx = Integer.parseInt(inp);
        System.out.println("Used a "+items[val].Name);
        items[val].Have=false;
        switch (val)
        {
            case 0:statusHeal(Roster[team[0][pidx]].Status,Roster[team[0][pidx]],backUp[team[0][pidx]]);
            Roster[team[0][pidx]].HP=backUp[team[0][pidx]].HP;
            System.out.println("Pokemon healed");
            break;
            case 1:
                if(Roster[team[0][pidx]].Status==-2)
                {
                    Roster[team[0][pidx]].HP=backUp[team[0][pidx]].HP;
                }
                statusHeal(-2,Roster[team[0][pidx]],backUp[team[0][pidx]]);

                break;
            case 2:statusHeal(Roster[team[0][pidx]].Status,Roster[team[0][pidx]],backUp[team[0][pidx]]);
                break;
                case 3:statusHeal(1,Roster[team[0][pidx]],backUp[team[0][pidx]]);
                break;
            case 4:statusHeal(17,Roster[team[0][pidx]],backUp[team[0][pidx]]);
            break;
            case 5:statusHeal(6,Roster[team[0][pidx]],backUp[team[0][pidx]]);
            break;
            case 6:Roster[team[0][pidx]].HP=Math.min(backUp[team[0][pidx]].HP,Roster[team[0][pidx]].HP+500);
                System.out.println("Pokemon healed");
            break;
        }
        return true;

    }
    static void statusHeal(int idx, Pokemon mon,Pokemon backup)
    {
        if(mon.Status==idx) {
            switch (idx) {
                case 1:mon.Attack=backup.Attack;
                System.out.println("Burn was healed");
                break;
                case 6:mon.Defense=backup.Defense;
                System.out.println("Poison was healed");
                break;
                case 17:mon.Speed=backup.Speed;
                System.out.println("Paralysis was healed");
                break;
                case -2:
                    System.out.println("Pokemon Revived");
                    break;
            }
            mon.Status=-1;

        }
        else
        {
            System.out.println("It had no effect");
        }
    }
    static double TypeEffect(double TypeEffective[][],int type1,int type2)
    {
        if(type2!=-1)
        return TypeEffective[type1][type2];
        else
        {
            return 1.0;
        }
    }

    static double damageCal(Pokemon off,Pokemon def,int val,double TypeEffective[][])
    {
        double damage=((2*100.0)/5+2)*off.Power[val];

        if(off.PhySpl[val])
        {
            damage*=off.SplAttack*1.0/def.SplDefense;
        }
        else
        {
            damage*=off.Attack*1.0/def.Defense;
        }

        damage=damage/50.0+2;


        if(off.Type1==off.AttType[val]||off.Type2==off.AttType[val])
            damage*=1.5;
        double multip=TypeEffect(TypeEffective,off.AttType[val],def.Type1)*TypeEffect(TypeEffective,off.AttType[val],def.Type2);
        if(multip==0)
        {
            effect="It does not affect "+def.Name;
        }
        else if(multip==1)
        {
            effect="";
        }
        else if(multip<1)
        {
            effect="It\'s not very effective";
        }
        else
        {
            effect="It\'s Super Effective";
        }
        damage=damage*multip;

        damage*=(Math.random()*2+8)/10.0;

        return damage;


    }


    static int OppAttk(Pokemon opp,Pokemon your,double TypE[][])
    {
        int max=-1;
        double damage=-1;
        for (int i=0;i<4;i++)
        {   double dam=damageCal(opp,your,i,TypE);
            if(dam>damage&&opp.PP[i]!=0)
        {
            damage=dam;
            max=i;
        }
        }

        return max;
    }

    static void statusCondition(int idx,Pokemon mon)
    {
        switch(idx)
        {
            case 1:System.out.println(mon.Name+" is burned");
            mon.Attack/=2;
            break;
            case 17:System.out.println(mon.Name+" is paralyzed. It may be unable to move");
            mon.Speed/=2;
            break;
            case 6: System.out.println(mon.Name+" is poisoned");
            mon.Defense/=2;
            break;
        }
        mon.Status=idx;
    }


    static void yourHit(Pokemon your,Pokemon opp, int val, double TypE[][])
    {
        if(your.Status==-2)
        {return;}
        int prob=(int)(Math.random()*10);
        if(your.Status==17&&prob<4)
        {
            System.out.println(your.Name+" is paralyzed. It cannot move");
            return;
        }
        double damage = damageCal(your, opp, val, TypE);
        System.out.println(your.Name + " used " + your.Moveset[val]);
        opp.HP -= (int) damage;
        prob=(int)(Math.random()*10);
        System.out.println(effect);
        if(opp.Status==-1&&damage!=0&&opp.Type1!=your.AttType[val]&& opp.Type2!=your.AttType[val])
        {
            if(prob<2)
            {
                statusCondition(your.AttType[val],opp);
            }
        }

        System.out.println(opp.Name+" HP:"+Math.max(0,opp.HP));
        your.PP[val]--;
        if(opp.HP<=0)
        {
            opp.HP=0;
            opp.Status=-2;
            System.out.println("Foe "+opp.Name+" fainted");
        }
    }

    static void oppHit(Pokemon your,Pokemon opp, double TypE[][])
    {
        if(opp.Status==-2)
        {return;}
        int prob=(int)(Math.random()*10);
        if(opp.Status==17&&prob<4)
        {
            System.out.println("Foe"+opp.Name+" is paralyzed. It cannot move");
            return;
        }
        int oppbest = OppAttk(opp, your, TypE);
        System.out.println("Foe "+opp.Name + " used " + opp.Moveset[oppbest]);
        opp.PP[oppbest]--;
        double damage = damageCal(opp, your, oppbest, TypE);
        System.out.println(effect);
        your.HP -= (int) damage;
        prob=(int)(Math.random()*10);
        if(your.Status==-1&&damage!=0&&your.Type1!=opp.AttType[oppbest]&& your.Type2!=opp.AttType[oppbest])
        {
            if(prob<2)
            {
                statusCondition(opp.AttType[oppbest],your);
            }
        }
        System.out.println(your.Name+" HP:"+Math.max(0,your.HP));
        if(your.HP<=0)
        {
            your.HP=0;
            your.Status=-2;
            System.out.println(your.Name+" fainted");
        }

    }


    static boolean Simulation(Pokemon[] Roster,Pokemon[] Backup,int[][] team,String[] TypeName,double TypeEffective[][],Item items[]) throws Exception
    {
        System.out.println("The battle begins");
        Scanner sc=new Scanner(System.in);
        int oppidx=0;
        int youridx=0;
        Pokemon your=Roster[team[0][youridx]];
        Pokemon opp=Roster[team[1][oppidx]];
        int yourPokemon=team[0].length;
        System.out.println("Opponent sent out "+opp.Name);

        System.out.println("Go "+your.Name);

        while(yourPokemon>0)
        {
            System.out.println("Enter\n1 for battle\n2 for items\n3 to change pokemon");

            String inp=sc.nextLine();
            if(validity(inp))
            {
                switch(Integer.parseInt(inp))
                {
                    case 1:System.out.println("Select the move from 1 to 4 & Select 0 to Get back to menu. The moves are shown below");
                    showMove(your,TypeName);
                    String move=sc.nextLine();
                    while(!validity(move)|| Integer.parseInt(move)>4)
                    {
                        System.out.println("Enter Valid");
                        move=sc.nextLine();
                    }

                    int val= Integer.parseInt(move);
                    if(val!=0&&your.PP[val-1]==0)
                    {
                        System.out.println("Out of PP");
                    }
                    else if(val!=0)
                    {
                        if (opp.Speed > your.Speed) {
                           oppHit(your,opp,TypeEffective);
                           yourHit(your,opp,val-1,TypeEffective);
                        } else {
                            yourHit(your,opp,val-1,TypeEffective);
                            oppHit(your,opp,TypeEffective);
                    }
                    }

                    break;


                    case 2:
                       if(itemWork(Roster,Backup,team,items))
                       {

                           oppHit(your,opp,TypeEffective);
                       }
                       else {
                           continue;
                       }
                        break;


                    case 3:
                        System.out.println("Choose the index of the Pokemon to switch into or 3 to exit to menu\nAvailable Pokemons");
                        int c=0;
                        for(int i=0;i<3;i++)
                        {
                            if(Roster[team[0][i]].Status!=-2)
                        {System.out.println(i+" "+Roster[team[0][i]].Name);
                        c++;}
                        }
                        if(c<=1)
                        {
                            System.out.println("No change Possible");
                            continue;
                        }
                        inp=sc.nextLine();
                        while(!validity(inp)||Integer.parseInt(inp)>3||(Integer.parseInt(inp) != 3 && Roster[team[0][Integer.parseInt(inp)]].Status == -2)||Integer.parseInt(inp)==youridx)
                        {
                            System.out.println("Not Valid/Already Fainted/On The Field");
                            inp=sc.nextLine();
                        }
                        val=Integer.parseInt(inp);
                        if(val!=3)
                        {
                            int oppbest = OppAttk(opp, your, TypeEffective);
                            System.out.println("Come back "+your.Name);
                            youridx=val;
                            your=Roster[team[0][youridx]];
                            System.out.println("Go "+your.Name);
                            int prob=(int)(Math.random()*10);
                            if(opp.Status==17&&prob<4)
                            {
                                System.out.println("Foe"+opp.Name+" is paralyzed. It cannot move");
                                continue;
                            }
                            System.out.println("Foe "+opp.Name + " used " + opp.Moveset[oppbest]);
                            opp.PP[oppbest]--;
                            double damage = damageCal(opp, your, oppbest, TypeEffective);
                            System.out.println(effect);
                            your.HP -= (int) damage;
                            System.out.println(your.Name+" HP:"+Math.max(0,your.HP));
                            if (your.HP <= 0) {
                                your.Status = -2;
                                your.HP = 0;
                                System.out.println(your.Name + " fainted");
                            }

                        }
                        break;


                    default:
                        System.out.println("Not Valid");
                        continue;


                }

                if(opp.Status==6)
                {
                    opp.HP-=(int)(Backup[team[1][oppidx]].HP/8.0);
                    System.out.println(opp.Name+" was hurt by poison");
                    if(opp.HP<=0)
                    {
                        opp.HP=0;
                        System.out.println(opp.Name+" fainted");
                        opp.Status=-2;
                    }
                }

                if(opp.Status==1)
                {
                    opp.HP-=(int)(Backup[team[1][oppidx]].HP/8.0);
                    System.out.println(opp.Name+" was hurt by burn");
                    if(opp.HP<=0)
                    {
                        opp.HP=0;
                        System.out.println(opp.Name+" fainted");
                        opp.Status=-2;
                    }
                }



                if(your.Status==6)
                {
                    your.HP-=(int)(Backup[team[0][youridx]].HP/8.0);
                    System.out.println(your.Name+" was hurt by poison");
                    if(your.HP<=0)
                    {
                        your.HP=0;
                        System.out.println(your.Name+" fainted");
                        your.Status=-2;
                    }
                }

                if(your.Status==1)
                {
                    your.HP-=(int)(Backup[team[0][youridx]].HP/8.0);
                    System.out.println(your.Name+" was hurt by burn");
                    if(your.HP<=0)
                    {
                        your.HP=0;
                        System.out.println(your.Name+" fainted");
                        your.Status=-2;
                    }
                }

                if(opp.HP==0)
                {
                    oppidx++;
                    if(oppidx==3)
                        return true;
                    else
                    {
                        opp=Roster[team[1][oppidx]];
                        System.out.println("Opponent sent out "+opp.Name);
                    }
                }

                if(your.HP==0)
                {
                    yourPokemon=0;
                    for(int i=0;i<3;i++)
                    {
                        if(Roster[team[0][i]].Status!=-2)
                        {
                            yourPokemon++;
                        }
                    }

                    if(yourPokemon!=0) {
                        System.out.println("Choose Another Pokemon");
                        for(int i=0;i<3;i++)
                        {   if(Roster[team[0][i]].Status!=-2)
                            System.out.println(i+" "+Roster[team[0][i]].Name);
                        }
                        inp=sc.nextLine();
                        while(!validity(inp)||Integer.parseInt(inp)>2||Roster[team[0][Integer.parseInt(inp)]].Status==-2)
                        {
                            System.out.println("Not Valid/Already Fainted");
                            inp=sc.nextLine();
                        }
                        youridx= Integer.parseInt(inp);
                        your=Roster[team[0][youridx]];
                        System.out.println("Go "+your.Name);
                    }


                }


            }
            else
            {
                System.out.println("Choose valid input");
            }

        }
        return false;


    }
    public static int play() throws Exception
    {
        DataBase db=new DataBase();
        Pokemon[] Roster=db.pokedex();
        db.ItemGrab();
        db.setTypeChart();
        DataBase db1=new DataBase();
        Pokemon[] Backup=db1.pokedex();
        effect="";
        System.out.println("Welcome to Pokemon Battle Simulator\nHere is your Roster");


        Item[] Items=db.items;
        double TypeEffective[][]=db.typeChart;
        String[] TypeName=db.Typing();


        for(int i=0;i<Roster.length;i++)
        {

            System.out.println(Roster[i].Name);
        }
        int team[][]=new int[2][3];
        Selection(team,Roster);
        System.out.println("The team has been selected \nYour Team");
        for(int i=0;i<3;i++)
        {
            System.out.println(Roster[team[0][i]].Name);
        }
        int money=0;
        if(Simulation(Roster,Backup,team,TypeName,TypeEffective,Items))
        {
            System.out.println("You WIN!");
            money=(int)(Math.random()*1000);
            System.out.println("Money Earned:"+money);
            return money;
        }
        else
        {
            System.out.println("You Lose");
            return 0;
        }



    }
}
