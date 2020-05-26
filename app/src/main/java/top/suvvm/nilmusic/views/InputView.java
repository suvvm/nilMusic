package top.suvvm.nilmusic.views;

import android.app.DatePickerDialog;
import android.content.Context;
import android.content.res.TypedArray;
import android.os.Build;
import android.text.InputType;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import top.suvvm.nilmusic.R;

/**
 * @ClassName: InputView
 * @Description: 可配置输入框，用于控制输入框的图标、提示内容、是否显示为密文
 * @Author: SUVVM
 * @Date: 2020/5/22 20:52
 */
public class InputView extends FrameLayout {
    private Integer inputIcon;
    private String inputHint;
    private Boolean isPsw;
    private View view;
    private ImageView imageView;
    private EditText editText;

    public InputView(@NonNull Context context) {
        super(context);
        init(context, null);
    }

    public InputView(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init(context, attrs);
    }

    public InputView(@NonNull Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context, attrs);
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    public InputView(@NonNull Context context, @Nullable AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        init(context, attrs);
    }
    // 初始化输入框
    private void init(Context context, AttributeSet attrs) {
        if (attrs == null) return;
        // 获取自定义属性
        TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.inputView);
        inputIcon = typedArray.getResourceId(R.styleable.inputView_input_icon, R.mipmap.nil_logo);
        inputHint = typedArray.getString(R.styleable.inputView_input_hint);
        isPsw = typedArray.getBoolean(R.styleable.inputView_is_psw, false);
        typedArray.recycle();
        // 获取layout资源文件，绑定layout布局
        view = LayoutInflater.from(context).inflate(R.layout.input_view, this, false);
        imageView = view.findViewById(R.id.iv_icon);
        editText = view.findViewById(R.id.et_input);
        // 关联布局与属性
        imageView.setImageResource(inputIcon);
        editText.setHint(inputHint);
        editText.setInputType(isPsw ? InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_PASSWORD : InputType.TYPE_CLASS_PHONE);

        addView(view);
    }
    // 获得输入框中的内容
    public String getInputVal () {
        return editText.getText().toString().trim();
    }
}
