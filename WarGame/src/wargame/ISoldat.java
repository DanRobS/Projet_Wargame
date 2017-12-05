package wargame;
public interface ISoldat 
{
   static enum TypesH 
   {
      HUMAIN (40,30,10,2,3), NAIN (80,10,20,0,1), ELF (70,50,10,6,5), HOBBIT (20,30,5,2,3);
      private final int POINTS_DE_VIE, PORTEE_VISUELLE, PUISSANCE, TIR, PORTEE_DEPLACEMENT;
      TypesH(int points, int portee, int puissance, int tir, int porteeDepl) 
      {
    	  POINTS_DE_VIE = points; 
    	  PORTEE_VISUELLE = portee;
    	  PUISSANCE = puissance; 
    	  TIR = tir;
    	  PORTEE_DEPLACEMENT = porteeDepl;
      }
      public int getPoints() { return POINTS_DE_VIE; }
      public int getPortee() { return PORTEE_VISUELLE; }
      public int getPuissance() { return PUISSANCE; }
      public int getTir() { return TIR; }
      public int getPorteeDepl() { return PORTEE_DEPLACEMENT; }
      public static TypesH getTypeHAlea() 
      {
         return values()[(int)(Math.random()*values().length)];
      }
   }
   
   
   public static enum TypesM 
   {
      TROLL (100,1,30,0,1), ORC (40,2,10,3,1), GOBELIN (20,2,5,2,1);
      private final int POINTS_DE_VIE, PORTEE_VISUELLE, PUISSANCE, TIR, PORTEE_DEPLACEMENT;
      TypesM(int points, int portee, int puissance, int tir, int porteeDepl) 
      {
    	  POINTS_DE_VIE = points; 
    	  PORTEE_VISUELLE = portee;
    	  PUISSANCE = puissance; 
    	  TIR = tir;
    	  PORTEE_DEPLACEMENT = porteeDepl;
      }
      public int getPoints() { return POINTS_DE_VIE; }
      public int getPortee() { return PORTEE_VISUELLE; }
      public int getPuissance() { return PUISSANCE; }
      public int getTir() { return TIR; } 
      public int getPorteeDepl() { return PORTEE_DEPLACEMENT; }
      public static TypesM getTypeMAlea() {
         return values()[(int)(Math.random()*values().length)];
      }
   }
   int getPoints(); 
   int getTour(); 
   int getPortee();
   void joueTour(int tour);
   void combat(Soldat soldat) throws FinDuJeu;
   void seDeplace(Position newPos);
}