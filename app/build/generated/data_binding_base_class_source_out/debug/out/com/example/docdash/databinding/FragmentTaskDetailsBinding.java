// Generated by view binder compiler. Do not edit!
package com.example.docdash.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.constraintlayout.widget.Group;
import androidx.viewbinding.ViewBinding;
import androidx.viewbinding.ViewBindings;
import com.example.docdash.R;
import java.lang.NullPointerException;
import java.lang.Override;
import java.lang.String;

public final class FragmentTaskDetailsBinding implements ViewBinding {
  @NonNull
  private final ConstraintLayout rootView;

  @NonNull
  public final Group PatientInformation;

  @NonNull
  public final Group TaskInformation;

  @NonNull
  public final Group TestsInformation;

  @NonNull
  public final Button taskDetailsFragmentMyTasksButton;

  @NonNull
  public final Button taskDetailsFragmentPatientDetailsButton;

  @NonNull
  public final TextView taskDetailsFragmentPatientName;

  @NonNull
  public final TextView taskDetailsFragmentPatientTitle;

  @NonNull
  public final TextView taskDetailsFragmentRoom;

  @NonNull
  public final TextView taskDetailsFragmentRoomTitle;

  @NonNull
  public final Button taskDetailsFragmentTakeTaskButton;

  @NonNull
  public final TextView taskDetailsFragmentTaskDescription;

  @NonNull
  public final TextView taskDetailsFragmentTaskDueTitle;

  @NonNull
  public final TextView taskDetailsFragmentTaskNo;

  @NonNull
  public final Button taskDetailsFragmentTaskPoolButton;

  @NonNull
  public final TextView taskDetailsFragmentTestTitle;

  @NonNull
  public final Button taskDetailsFragmentTestsDetailsButton;

  @NonNull
  public final TextView taskDetailsFragmentTestsName;

  private FragmentTaskDetailsBinding(@NonNull ConstraintLayout rootView,
      @NonNull Group PatientInformation, @NonNull Group TaskInformation,
      @NonNull Group TestsInformation, @NonNull Button taskDetailsFragmentMyTasksButton,
      @NonNull Button taskDetailsFragmentPatientDetailsButton,
      @NonNull TextView taskDetailsFragmentPatientName,
      @NonNull TextView taskDetailsFragmentPatientTitle, @NonNull TextView taskDetailsFragmentRoom,
      @NonNull TextView taskDetailsFragmentRoomTitle,
      @NonNull Button taskDetailsFragmentTakeTaskButton,
      @NonNull TextView taskDetailsFragmentTaskDescription,
      @NonNull TextView taskDetailsFragmentTaskDueTitle,
      @NonNull TextView taskDetailsFragmentTaskNo,
      @NonNull Button taskDetailsFragmentTaskPoolButton,
      @NonNull TextView taskDetailsFragmentTestTitle,
      @NonNull Button taskDetailsFragmentTestsDetailsButton,
      @NonNull TextView taskDetailsFragmentTestsName) {
    this.rootView = rootView;
    this.PatientInformation = PatientInformation;
    this.TaskInformation = TaskInformation;
    this.TestsInformation = TestsInformation;
    this.taskDetailsFragmentMyTasksButton = taskDetailsFragmentMyTasksButton;
    this.taskDetailsFragmentPatientDetailsButton = taskDetailsFragmentPatientDetailsButton;
    this.taskDetailsFragmentPatientName = taskDetailsFragmentPatientName;
    this.taskDetailsFragmentPatientTitle = taskDetailsFragmentPatientTitle;
    this.taskDetailsFragmentRoom = taskDetailsFragmentRoom;
    this.taskDetailsFragmentRoomTitle = taskDetailsFragmentRoomTitle;
    this.taskDetailsFragmentTakeTaskButton = taskDetailsFragmentTakeTaskButton;
    this.taskDetailsFragmentTaskDescription = taskDetailsFragmentTaskDescription;
    this.taskDetailsFragmentTaskDueTitle = taskDetailsFragmentTaskDueTitle;
    this.taskDetailsFragmentTaskNo = taskDetailsFragmentTaskNo;
    this.taskDetailsFragmentTaskPoolButton = taskDetailsFragmentTaskPoolButton;
    this.taskDetailsFragmentTestTitle = taskDetailsFragmentTestTitle;
    this.taskDetailsFragmentTestsDetailsButton = taskDetailsFragmentTestsDetailsButton;
    this.taskDetailsFragmentTestsName = taskDetailsFragmentTestsName;
  }

  @Override
  @NonNull
  public ConstraintLayout getRoot() {
    return rootView;
  }

  @NonNull
  public static FragmentTaskDetailsBinding inflate(@NonNull LayoutInflater inflater) {
    return inflate(inflater, null, false);
  }

  @NonNull
  public static FragmentTaskDetailsBinding inflate(@NonNull LayoutInflater inflater,
      @Nullable ViewGroup parent, boolean attachToParent) {
    View root = inflater.inflate(R.layout.fragment_task_details, parent, false);
    if (attachToParent) {
      parent.addView(root);
    }
    return bind(root);
  }

  @NonNull
  public static FragmentTaskDetailsBinding bind(@NonNull View rootView) {
    // The body of this method is generated in a way you would not otherwise write.
    // This is done to optimize the compiled bytecode for size and performance.
    int id;
    missingId: {
      id = R.id.PatientInformation;
      Group PatientInformation = ViewBindings.findChildViewById(rootView, id);
      if (PatientInformation == null) {
        break missingId;
      }

      id = R.id.TaskInformation;
      Group TaskInformation = ViewBindings.findChildViewById(rootView, id);
      if (TaskInformation == null) {
        break missingId;
      }

      id = R.id.TestsInformation;
      Group TestsInformation = ViewBindings.findChildViewById(rootView, id);
      if (TestsInformation == null) {
        break missingId;
      }

      id = R.id.taskDetailsFragmentMyTasksButton;
      Button taskDetailsFragmentMyTasksButton = ViewBindings.findChildViewById(rootView, id);
      if (taskDetailsFragmentMyTasksButton == null) {
        break missingId;
      }

      id = R.id.taskDetailsFragmentPatientDetailsButton;
      Button taskDetailsFragmentPatientDetailsButton = ViewBindings.findChildViewById(rootView, id);
      if (taskDetailsFragmentPatientDetailsButton == null) {
        break missingId;
      }

      id = R.id.taskDetailsFragmentPatientName;
      TextView taskDetailsFragmentPatientName = ViewBindings.findChildViewById(rootView, id);
      if (taskDetailsFragmentPatientName == null) {
        break missingId;
      }

      id = R.id.taskDetailsFragmentPatientTitle;
      TextView taskDetailsFragmentPatientTitle = ViewBindings.findChildViewById(rootView, id);
      if (taskDetailsFragmentPatientTitle == null) {
        break missingId;
      }

      id = R.id.taskDetailsFragmentRoom;
      TextView taskDetailsFragmentRoom = ViewBindings.findChildViewById(rootView, id);
      if (taskDetailsFragmentRoom == null) {
        break missingId;
      }

      id = R.id.taskDetailsFragmentRoomTitle;
      TextView taskDetailsFragmentRoomTitle = ViewBindings.findChildViewById(rootView, id);
      if (taskDetailsFragmentRoomTitle == null) {
        break missingId;
      }

      id = R.id.taskDetailsFragmentTakeTaskButton;
      Button taskDetailsFragmentTakeTaskButton = ViewBindings.findChildViewById(rootView, id);
      if (taskDetailsFragmentTakeTaskButton == null) {
        break missingId;
      }

      id = R.id.taskDetailsFragmentTaskDescription;
      TextView taskDetailsFragmentTaskDescription = ViewBindings.findChildViewById(rootView, id);
      if (taskDetailsFragmentTaskDescription == null) {
        break missingId;
      }

      id = R.id.taskDetailsFragmentTaskDueTitle;
      TextView taskDetailsFragmentTaskDueTitle = ViewBindings.findChildViewById(rootView, id);
      if (taskDetailsFragmentTaskDueTitle == null) {
        break missingId;
      }

      id = R.id.taskDetailsFragmentTaskNo;
      TextView taskDetailsFragmentTaskNo = ViewBindings.findChildViewById(rootView, id);
      if (taskDetailsFragmentTaskNo == null) {
        break missingId;
      }

      id = R.id.taskDetailsFragmentTaskPoolButton;
      Button taskDetailsFragmentTaskPoolButton = ViewBindings.findChildViewById(rootView, id);
      if (taskDetailsFragmentTaskPoolButton == null) {
        break missingId;
      }

      id = R.id.taskDetailsFragmentTestTitle;
      TextView taskDetailsFragmentTestTitle = ViewBindings.findChildViewById(rootView, id);
      if (taskDetailsFragmentTestTitle == null) {
        break missingId;
      }

      id = R.id.taskDetailsFragmentTestsDetailsButton;
      Button taskDetailsFragmentTestsDetailsButton = ViewBindings.findChildViewById(rootView, id);
      if (taskDetailsFragmentTestsDetailsButton == null) {
        break missingId;
      }

      id = R.id.taskDetailsFragmentTestsName;
      TextView taskDetailsFragmentTestsName = ViewBindings.findChildViewById(rootView, id);
      if (taskDetailsFragmentTestsName == null) {
        break missingId;
      }

      return new FragmentTaskDetailsBinding((ConstraintLayout) rootView, PatientInformation,
          TaskInformation, TestsInformation, taskDetailsFragmentMyTasksButton,
          taskDetailsFragmentPatientDetailsButton, taskDetailsFragmentPatientName,
          taskDetailsFragmentPatientTitle, taskDetailsFragmentRoom, taskDetailsFragmentRoomTitle,
          taskDetailsFragmentTakeTaskButton, taskDetailsFragmentTaskDescription,
          taskDetailsFragmentTaskDueTitle, taskDetailsFragmentTaskNo,
          taskDetailsFragmentTaskPoolButton, taskDetailsFragmentTestTitle,
          taskDetailsFragmentTestsDetailsButton, taskDetailsFragmentTestsName);
    }
    String missingId = rootView.getResources().getResourceName(id);
    throw new NullPointerException("Missing required view with ID: ".concat(missingId));
  }
}
