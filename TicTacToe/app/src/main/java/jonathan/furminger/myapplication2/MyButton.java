package jonathan.furminger.myapplication2;

import android.content.Context;
import android.util.AttributeSet;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

public class MyButton extends androidx.appcompat.widget.AppCompatButton {

    private int row = 0;
    private int col = 0;
    private int status = 0;

    public MyButton(@NonNull Context context) {
        super(context);
    }

    public MyButton(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public MyButton(@NonNull Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

//    public MyButton(@NonNull Context context, int row, int col) {
//        super(context);
//        this.row = row;
//        this.col = col;
//    }

    public int getRow() {
        return row;
    }

    public int getCol() {
        return col;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public int getStatus() {
        return status;
    }


}
