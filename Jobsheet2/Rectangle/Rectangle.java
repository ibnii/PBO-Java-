package Rectangle;

public class Rectangle {
    int width, height;
    
    public Rectangle(int width, int height) {
        this.width = width;
        this.height = height;
    }
    
    int area(){
        return width*height;
    }


    int perimeter(){
        return 2 * (width+height);
    }

    
}
