public class Pokemon {
    String Name;                //Class for the pokemon Stats
    long HP;
    long Attack;
    long Defense;
    long SplAttack;
    long SplDefense;
    long Speed;
    int Type1;
    int Type2;
    String[] Moveset;
    int[] PP;
    long[] Power;
    boolean[] PhySpl;
    int[] AttType;
    int Status;
    Pokemon(String n, long hp, long att, long def,long satt, long sdef, long spd, int typ1,int typ2,String[] move,int[] pp,long[] pow,boolean[] ps,int[] atyp)
    {
        Name=n;
        HP=hp;
        Attack=att;
        Defense=def;
        SplAttack=satt;
        SplDefense=sdef;
        Speed=spd;
        Type1=typ1;
        Type2=typ2;
        Moveset=move;
        PP=pp;
        Power=pow;
        PhySpl=ps;
        AttType=atyp;
        Status=-1;
    }
}
