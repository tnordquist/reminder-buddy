package edu.cnm.deepdive.reminderbuddy.controller;

import android.os.Bundle;
import android.provider.CalendarContract.Reminders;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;
import com.google.android.material.snackbar.Snackbar;
import edu.cnm.deepdive.reminderbuddy.databinding.FragmentReminderBinding;
import edu.cnm.deepdive.reminderbuddy.model.entity.Card;
import edu.cnm.deepdive.reminderbuddy.viewmodel.CardViewModel;
import edu.cnm.deepdive.reminderbuddy.viewmodel.ReminderViewModel;
import java.util.List;

public class ReminderFragment extends Fragment {

  private ReminderViewModel reminderViewModel;
  private List<Card> cards;
  private FragmentReminderBinding binding;


  @Nullable
  @Override
  public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
      @Nullable Bundle savedInstanceState) {
    binding = FragmentReminderBinding.inflate(inflater, container, false);
    return binding.getRoot();

  }

  // TODO override onviewcreated and connect to a viewmodel.
  @SuppressWarnings("ConstantConditions")
  @Override
  public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
    super.onViewCreated(view, savedInstanceState);
    reminderViewModel = new ViewModelProvider(getActivity()).get(ReminderViewModel.class));
    reminderViewModel
        .getThrowable()
        .observe(getViewLifecycleOwner(), this::handleThrowable);
    reminderViewModel
        .getReminder()
        .observe(getViewLifecycleOwner(), this :: updateGameDisplay);
  }

  private void updateGameDisplay(Reminders reminders) {
  }


  @Override
  public void onDestroyView() {
    binding = null;
    super.onDestroyView();
  }

  // TODO Have Reminder Fragment get a randomized list of cards to allow the user to "test"
  public List<Card> getCards() {
    return cards;
  }

  private void handleThrowable(Throwable throwable) {
    if (throwable != null) {
      //noinspection ConstantConditions
      Snackbar
          .make(binding.getRoot(), throwable.getMessage(), Snackbar.LENGTH_LONG)
          .show();
    }
  }

  
}