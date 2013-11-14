package com.wemakestuff.teracast.ui.widget.view;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.CheckBox;

public class FocusableCheckBox extends CheckBox {
	public FocusableCheckBox(Context context) {
		super(context);
	}

	public FocusableCheckBox(Context context, AttributeSet attrs) {
		super(context,attrs);
	}

	public FocusableCheckBox(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
	}

	@Override
	public void setPressed(final boolean pressed) {
		if (pressed && getParent() instanceof View && ((View) getParent()).isPressed()) {
			return;
		}
		super.setPressed(pressed);
	}
}
