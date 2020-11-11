package xyz.liangwh.simplelogger4j.testData;

import java.util.Random;

public class AiRandData {

    public static float afRandomFloat[] = new float[10000];
    public static  int aiRandomInt[] = new int[10000];
    public static String  asRandomStr[] = new String[10000];


    public static String str[] = new String[]{
            "s",
            "4i",
            "dJh",
            "SdPY",
            "Tv|IN",
            "LS3[jM",
            "3A]qt4+",
            "5S7FYqO4",
            "rzTcGEDjL",
            "hp2DA.Vawg",
            "+kXj{;ZYo;j",
            "2ly953YzQ{Pw",
            "yyzeEv9x,NsY1",
            "G2{.X:vaW{JyoT",
            "3brhiLoYag4xE6F",
            "6kU5}G2o{bz]8pU;",
            "FLik3VNq;d1'eT{Ad",
            "-xQWnfj=bYp98LeWKI",
            "JJjfzfus6B5Osp[x4E|",
            "S}V=NSxLjUnl7oJgmUUv",
            "CqXCRu9kPxoe4J+2oOguy",
            "sgRV48;{rkzflsFlVc1HxH",
            "'h6p;mt5IDBv1EOoObgcHJe",
            "uLJlTDYmYsXaYs5u9gbB4ZZG",
            "6vNMbLU4cgEe}iBnMSoBwOKt3",
            "S18Stf,Z6LDptJGId-aoamYxdp",
            "7{W3=E6Mq]jfe|.ACiw9gNavwU.",
            "oQkrFXeqQo[C=3FH[w2lYT{8v9ah",
            "R}|Z6yrgiP-}.JnVPF=3ro;wGCaMR",
            "prx[yNvNk64[Y'v[lkS4JXu6c2QT+m",
            "X1Dv7-Kq+zi}M6u,TI5trrTGW+4hy6o",
            "idLIFiVI9dhSGk2nCNtSbciIxojPYWUu",
            "g[Ry1BiSKAuASH]aAig.Ezrxpd8i'sP7H",
            "OfYtrs41apdbTwhzXn3aDSby[QZ6KkBTqm",
            ".P7:Z5J8ufFQEqPIxXrpPSxnVeRKYz]1l6d",
            "{DsBkSCc9nlgte]+SxzCvUm:64Rhibp=MfRh",
            "2QhVp3bro'Nk9;Br}OQnE8ZgDWuw3M{{FG:Sp",
            "AhCPcf2H6XWpjz4Le4gx+mBob163582MivW9Tr",
            "PbBhh[xkJ4p81dlgu3{162biShJXDN'eSZCt;Yc",
            "W}WVxp.bHxHtpZ5-zvHLZnmRcn4R=,Mo14y8ek;w",
            "Ix6;vuNNzKO4ak{1rnxvp1Zrlqc+hffO-iGEtNQ5R",
            "|9FuFWltb6NWnox.eOf5Y8LMThDwCXZbaBtqc2wOnH",
            "HM|lO4]cXnByrSjhTF1fygaH58JPl3mUTt6;C[75.fl",
            "y3X.TnmmV;UsIvYC]hoVM7bqZCRQTPyj45RpxeKmAkG[",
            "jBHOH[8j[ZZkv;7d;yCW5IudAO+vhJa9ma9ygY|{ZyFnC",
            ".u|T'UnSCLMXMx'BQP-fWLhliYwSKkouu{aAdb{YhqK7y3",
            "Iu8+sYuRdkTbJwe[5eQUnTrq]v]'SDRGGx3BedEoiBU'Ac3",
            "zcyCm,gCnU=uRC'dGCVgvpT7|2dXXj+AzXWIVGvFZAeD{XTV",
            "VLdob4|N::2fvnopi{wYda:M}mAd8q4gueXJz1IWN8cqEjshN",
            "wnZ5jT7:rT{JEJ,;BjSFiCHeOHTkJXJZ'b-V|9a{j67B[5Yom3",
            "IcKPQ;c{=sfZCm1-K245jLm1K7v5f9JfOoODEj+BOMHiH2phmno",
            "LC'FZa8ImYXKmDavoV8mycpyGTsD{4;[i4PlP{73HfAxGcH.QCJE",
            "EbZ}x:Pg1N9}iW,SBAuKlkmnDrgU2nh{Q3Meo.+kbLuD-S2zYNMYl",
            "APK]j|HwVm2gcNZtRmtGFZ1JvUF8WLyX8e1tQ;b27:Hm-6GV9fQDWL",
            "JZ1[XQJ2Q7tG]o|Z7mk=YB'iYc+XmSE|1aJ5lG=s'w5cpZXF[+us3St",
            "[36gbdS38B|KwEjTx1lCdR2mWGXgAGOwoRTEpmc=u{Nk5iHvRtUCk4XH",
            "'[N5WZBYcmbM14EtoDBIbJjXU9iNCMb[Ntz:s{q7e9,nEkRp9ZRkKU,Gx",
            ",6wqmvRvb:-jWG9GTVEGoFLPE5OIAbiJsl4Y2f;|X}{g}PtlUcZ=1z9EI;",
            "g.O3e8VBIf|-51f[,'chf]GhRqzNx-tgb=iwyy'4svRPkd]TcZncsuwEMjW",
            "d5m4VGYmdbi4cKoXrgIk-sh2pUGYf7wHiQJQnDvCp+eatP1s9f98D-3PvFm7",
            "}67wU4HVGwE:rjf=tUR2afZGNQ2y6bQ:ToCc4N[Il.kB1,NhNk|+M'7LMQo[S",
            "hr.g1Lcb2zkpeRqSyil,R5Rw6EhykE[4c}FYlkVe5-IMJ4TJ3L6,yW{oUYK=cX",
            "IdCw43BOhH6YxWiYHnVnmRVRTPSLhdBQ|xC:25BcufWIX+vXYK4CxE{BwW]usbH",
            "JCYmQuHMhmaJFdm{yV+m2;kLBAeDLaMRJW.|8o1y3TVNtszV{FsSRWjB9m,XHgNj",
            "|dFWxAbH;bKM24GqaOAO4MajSEiD.MTSxq51reJ=LUo|lIkY{Mtu3k5mH1Rf[gj}-",
            "4BNo2suQLMxCG-CbjZtau3+OcehogUJpS8fzCiIFj7RmbRkoEx-pzwitWVb{=JvY9A",
            "wMFkpYgQ5Q4ZrhiQeOBpTt]7LC:CzM51d;lS+W4Yb,J1UxZlki]27nzUD3KY+[9RTXN",
            "qGlgQ=.zfQCU|;5cn8JBx9XiaCme6eWxHKlF7mV97nULSAwsRzm]1CcwC;v}V'crQbFp",
            "qywUJQHgf|FzAHyNRj'ST=yHuUdB}EgDS,:Ahf:P2xpYrH|R3IG1VL4uYt7FM89ow}nZS",
            "qXrGiLjMYKQt6j7dARYI.48Bs9ib75}O:HD7r8O.z{U[p+=tqjjtVekedtqgM6NM1BzUBU",
            "+4EPm-Q'ysZV:3ffqCDbxL,v+rpL6bIEPsB1fFw:kTU=OOJ}1A5PAEl4yG|Wzizm}iIF6w8",
            "glmHr=[CuDDOj}oQaCKwirQNfs2MXD,m1-I-VbpJ7L'eBcAUvz7Ykvd55n52uQ4Wtx4Lqc+g",
            "NBoeT4gL=3W7rDPOOv11igBcZKbsWkbI]unZzBzHUEHJUSZy9UIqGVicwe3FEwW6;j+QG5tzr",
            "c3[qU8CGQi.]EKe3sJZCDW8HiUfTn=uz[H-Gags1skbA.}tAl,4hENCNsvg7jl8c9piWlQ6zxR",
            "FzKBSTrp]aqMMsRR3zIEjOwykGByoUz{M[EN=RA+.K9ShSe9SlG43,rstg9ekR}nBmD|XamBAjZ",
            "1JIsUY8QKyWUi3HXJZw7i+FUqgwDC9rGVyGePp-1o9'Pawz5IJ7qSyVWu4+SdELSn4XgW5T87mZn",
            "yqG7j=nbr1PEPFSVIYJ'6NIXPpz8'4RskrmLCxN-zK9.3lDE7sTL]q3xD=fH4J92v;GmQem45tpW7",
            "AiwofPP:u1Dx=pCx6f{=r+Yw|5VTucvOtC1n:vOHk9OYOrSJ58Sit+zJ4a66UhlOMg}GRyBAzshfrL",
            "c2OFepgsr;wcJjLtXh3}48u}8L}rW7aNG5XSpXY1eKZFxgvtSYnDKV'm|iOvxWO]sEAH'+L'YVklRX7",
            "L7P7FYrpJ1-kIgw=|AaRfr-[cNfiX8mV,=blbNICquCxmforMyJHskGdk3{x+dOblwrj4RuZUOTsyfm-",
            "UhWYtZn{RcOh{MwaUe-TCrGatpnyIWFvVf5d89QO.q9PZRjs-srgMWS7[6SP4s,FnCql9jQxVgXAdMBaz",
            "1nzH2Z7P'FG;CZfHIsoNsoOp15p5VOnX'}ROH=BqYyeMGlwjvxMTzctIFYE,xu2[7Y9:if+FWkc5VAwaSz",
            "-1oMzwG3DllQf7eTv|5SX[r9tMShsTH'-axHn1nMIfbdu;If4BJOo+p5nilZZd8',oo2jri]IAu1-PN]YEJ",
            "DkxtnEAdYzRSJR[z-fMXwJ'7jdu[seFkdHMBUnLXLcY8MaEqUz,|;Crg2O2pu9eAHGG3j9cyb1Yfoqvs}p4X",
            "Fc175DAt1yakLWe43FzTvYSdlzN2;jdXO3RkM{prVcqEprviN1qnG57mdDr+UGnEZAYwV9Nq8JLPoxVZksdYO",
            "RYOPaaP;7HwddBX2vsOmzGKEqfTCYzw;-R6aw3Lm|VuBlREgRQ2raoUmXwR[+cgq,pBA9'TeDGSZBJH{xy9wxm",
            "EYWT;UaxmFnhPP=ghQWO{x1r;ckYvWdT9cHBC-6rKNiRY]LKBwa'ARFlx]jy8tcT7R}lqnLa{NP'b{3yJutJoY:",
            "AOd1P47CDpVFKu-W6i':gw=NPPi9DUcu4ivPbV}BOkExMVW2IqGQfzRiFnhUOt9ta9vC-LzgKKGjmNOqV:g16cCW",
            ":lqH9CwUia|jz4s8yN7uIn1RBrIIf2g2yYpEXKsUAX9TwO3Pzi1opH]kW'J8YWUw6CKfup7SK:P558jBRFWfuSDyd",
            "|jnaRfMtvdNtpMlqmUuxsNDwURqPfF[B95ApopAxUiHze{kW6T1ub3KqWsgvQK7dSVAPqJmrt}2[aZ3ZTile[bwds1",
            "xRW5t}yEmHJbHjafSuWqPe9n:IeW4bi8Coe4P8ChK2u,wH=Pb=Yq1fMD5TRs;AT6xHo2h+SKkH[2rwYm3-YgE|KTUfK",
            "n{xqPUuYa2bJ'BxbcXD2VqAuOxV6q:ogfp3j;Gg7e9u1Mc|ZIwGGH69y+pmO:CwAWh4z]+RNlXYcjkhwo7b8sJ'dNEa4",
            "KJWS8'65WVUpXh9xEUufz9boRcIs-=dq{CVTS1G4q,=YvBVgDsQVBadxSdO[yjCswlnyk:zLLNZNunqtoY8y5kaPA3D8F",
            "asvkvZmBbRrfeP|R]DA;XvKFl.d:4;PytcOdM38xVB2=gu8SJS1w-[4mAS1S'lGyE9FnTLI6kziZ,sCtGwy|H.S3It[oa7",
            "JMeg,8yD2ZZnNFay[RVtP8qREy+kDCWBmCf3i1M+EJ2xVAsi}EAXH2FgLgLBdtMRfh3tY8E|wY9dbclh1IhKF1WG9svVYFU",
            "I:aELQVqhXcQ|]Vf-oSl;rw]9InNhe4g6iGgFnfsDrkZG}r4IS7iK}KDP1|U:3Sn55efHb{ZZ6cAn8sTwA+kTdjJBwhxYxSP",
            "mGsZEm9EIXN:|v,Y},lAA9G,VLO;yPE5Q47XTjiZ'51.nlgHY3S[5+m7W-Oax9jJhyWnNep={ah4UzAHxwslcVJwfy-9}LArB",
            "Ix5E]RDe6=8hLvFwPFhtsRueJZAR:6mHdB9j'KbXo64oicKVGiKaglaFos}3jY2lWUXNnML9jfm5G7AF'lOabrNcsIO,d:.Jkt",
            "FApCm{gI54;}Pc|uBei2obqAWZ8nJD1UtF4uIo,sEH3[bzCMzNP4d1zAAkJqyOZ7=bXA2jif7]2Gol85.NIrfn}xi3BRTuqvFnw",
            "zs4DCzXnyRt1YNe'MLoD3BUpB;zionKPuURJ8RfqWUrN72Ss5nCc4FZIr5TdO{wQ9-1STqQBOk2KS9VdiPMypv.=x{,uYNJ'HcK4",
            "s"
    };
    static {
        Random random = new Random();
        for(int i=0;i<10000;i++){
            afRandomFloat[i] = random.nextInt(i+1);
            afRandomFloat[i] = random.nextFloat();
            asRandomStr[i] = str[i%100];
        }
    }
}