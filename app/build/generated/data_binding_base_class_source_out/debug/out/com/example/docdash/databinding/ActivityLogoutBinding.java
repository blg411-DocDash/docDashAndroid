// Generated by view binder compiler. Do not edit!
package com.example.docdash.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.viewbinding.ViewBinding;
import androidx.viewbinding.ViewBindings;
import com.example.docdash.R;
import java.lang.NullPointerException;
import java.lang.Override;
import java.lang.String;

public final class ActivityLogoutBinding implements ViewBinding {
  @NonNull
  private final ConstraintLayout rootView;

  @NonNull
  public final Button buttonApply;

  @NonNull
  public final Button buttonLogout;

  @NonNull
  public final Button buttonMyTasks1;

  @NonNull
  public final Button buttonTaskPool1;

  @NonNull
  public final ConstraintLayout constraintLayout;

  @NonNull
  public final TextView dummyMiddleButton;

  @NonNull
  public final EditText editTextListLimitSetting;

  @NonNull
  public final TextView textEmailHolder;

  @NonNull
  public final TextView textNameHolder;

  @NonNull
  public final TextView textViewEmailTitle;

  @NonNull
  public final TextView textViewLimitSetting;

  @NonNull
  public final TextView textViewLogout;

  @NonNull
  public final TextView textViewNameTitle;

  private ActivityLogoutBinding(@NonNull ConstraintLayout rootView, @NonNull Button buttonApply,
      @NonNull Button buttonLogout, @NonNull Button buttonMyTasks1, @NonNull Button buttonTaskPool1,
      @NonNull ConstraintLayout constraintLayout, @NonNull TextView dummyMiddleButton,
      @NonNull EditText editTextListLimitSetting, @NonNull TextView textEmailHolder,
      @NonNull TextView textNameHolder, @NonNull TextView textViewEmailTitle,
      @NonNull TextView textViewLimitSetting, @NonNull TextView textViewLogout,
      @NonNull TextView textViewNameTitle) {
    this.rootView = rootView;
    this.buttonApply = buttonApply;
    this.buttonLogout = buttonLogout;
    this.buttonMyTasks1 = buttonMyTasks1;
    this.buttonTaskPool1 = buttonTaskPool1;
    this.constraintLayout = constraintLayout;
    this.dummyMiddleButton = dummyMiddleButton;
    this.editTextListLimitSetting = editTextListLimitSetting;
    this.textEmailHolder = textEmailHolder;
    this.textNameHolder = textNameHolder;
    this.textViewEmailTitle = textViewEmailTitle;
    this.textViewLimitSetting = textViewLimitSetting;
    this.textViewLogout = textViewLogout;
    this.textViewNameTitle = textViewNameTitle;
  }

  @Override
  @NonNull
  public ConstraintLayout getRoot() {
    return rootView;
  }

  @NonNull
  public static ActivityLogoutBinding inflate(@NonNull LayoutInflater inflater) {
    return inflate(inflater, null, false);
  }

  @NonNull
  public static ActivityLogoutBinding inflate(@NonNull LayoutInflater inflater,
      @Nullable ViewGroup parent, boolean attachToParent) {
    View root = inflater.inflate(R.layout.activity_logout, parent, false);
    if (attachToParent) {
      parent.addView(root);
    }
    return bind(root);
  }

  @NonNull
  public static ActivityLogoutBinding bind(@NonNull View rootView) {
    // The body of this method is generated in a way you would not otherwise write.
    // This is done to optimize the compiled bytecode for size and performance.
    int id;
    missingId: {
      id = R.id.buttonApply;
      Button buttonApply = ViewBindings.findChildViewById(rootView, id);
      if (buttonApply == null) {
        break missingId;
      }

      id = R.id.buttonLogout;
      Button buttonLogout = ViewBindings.findChildViewById(rootView, id);
      if (buttonLogout == null) {
        break missingId;
      }

      id = R.id.buttonMyTasks_1;
      Button buttonMyTasks1 = ViewBindings.findChildViewById(rootView, id);
      if (buttonMyTasks1 == null) {
        break missingId;
      }

      id = R.id.buttonTaskPool_1;
      Button buttonTaskPool1 = ViewBindings.findChildViewById(rootView, id);
      if (buttonTaskPool1 == null) {
        break missingId;
      }

      id = R.id.constraintLayout;
      ConstraintLayout constraintLayout = ViewBindings.findChildViewById(rootView, id);
      if (constraintLayout == null) {
        break missingId;
      }

      id = R.id.dummyMiddleButton;
      TextView dummyMiddleButton = ViewBindings.findChildViewById(rootView, id);
      if (dummyMiddleButton == null) {
        break missingId;
      }

      id = R.id.editTextListLimitSetting;
      EditText editTextListLimitSetting = ViewBindings.findChildViewById(rootView, id);
      if (editTextListLimitSetting == null) {
        break missingId;
      }

      id = R.id.textEmailHolder;
      TextView textEmailHolder = ViewBindings.findChildViewById(rootView, id);
      if (textEmailHolder == null) {
        break missingId;
      }

      id = R.id.textNameHolder;
      TextView textNameHolder = ViewBindings.findChildViewById(rootView, id);
      if (textNameHolder == null) {
        break missingId;
      }

      id = R.id.textViewEmailTitle;
      TextView textViewEmailTitle = ViewBindings.findChildViewById(rootView, id);
      if (textViewEmailTitle == null) {
        break missingId;
      }

      id = R.id.textViewLimitSetting;
      TextView textViewLimitSetting = ViewBindings.findChildViewById(rootView, id);
      if (textViewLimitSetting == null) {
        break missingId;
      }

      id = R.id.textViewLogout;
      TextView textViewLogout = ViewBindings.findChildViewById(rootView, id);
      if (textViewLogout == null) {
        break missingId;
      }

      id = R.id.textViewNameTitle;
      TextView textViewNameTitle = ViewBindings.findChildViewById(rootView, id);
      if (textViewNameTitle == null) {
        break missingId;
      }

      return new ActivityLogoutBinding((ConstraintLayout) rootView, buttonApply, buttonLogout,
          buttonMyTasks1, buttonTaskPool1, constraintLayout, dummyMiddleButton,
          editTextListLimitSetting, textEmailHolder, textNameHolder, textViewEmailTitle,
          textViewLimitSetting, textViewLogout, textViewNameTitle);
    }
    String missingId = rootView.getResources().getResourceName(id);
    throw new NullPointerException("Missing required view with ID: ".concat(missingId));
  }
}