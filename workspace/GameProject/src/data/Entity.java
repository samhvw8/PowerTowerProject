package data;

public interface Entity {

    public float getX();

    public float getY();

    public void setX(float x);

    public void setY(float y);

    public void setWidth(int width);

    public int getWidth();

    public void setHeight(int height);

    public int getHeight();

    public void update();

    public void draw();
}
