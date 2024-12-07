//ISAAC DAVID PATERNINA NOBLE 0222220022
//JESUS ALBERTO OSORIO GÓMEZ 0222220048
//JOSE FELIX BUSTAMANTE SOTO 0222220254
package arkanoid.records;


public class Records {
    
    String name;
    int score;
    int Balls;
    String Speed;

    public Records(String name, int score, int Balls, String Speed) {
        this.name = name;
        this.score = score;
        this.Balls = Balls;
        this.Speed = Speed;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public int getBalls() {
        return Balls;
    }

    public void setBalls(int Balls) {
        this.Balls = Balls;
    }

    public String getSpeed() {
        return Speed;
    }

    public void setSpeed(String Speed) {
        this.Speed = Speed;
    }
    
    
    
    
    
}
