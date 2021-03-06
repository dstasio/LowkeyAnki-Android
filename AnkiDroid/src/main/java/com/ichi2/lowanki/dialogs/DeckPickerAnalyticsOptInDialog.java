package com.ichi2.lowanki.dialogs;

import android.content.res.Resources;
import android.os.Bundle;

import com.afollestad.materialdialogs.MaterialDialog;
import com.ichi2.lowanki.LowkeyAnkiDroidApp;
import com.ichi2.lowanki.DeckPicker;
import com.ichi2.lowanki.R;
import com.ichi2.lowanki.analytics.AnalyticsDialogFragment;
import com.ichi2.lowanki.analytics.UsageAnalytics;

import androidx.annotation.NonNull;

public class DeckPickerAnalyticsOptInDialog extends AnalyticsDialogFragment {
    public static DeckPickerAnalyticsOptInDialog newInstance() {
        return new DeckPickerAnalyticsOptInDialog();
    }

    @NonNull
    @Override
    public MaterialDialog onCreateDialog(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Resources res = getResources();
        return new MaterialDialog.Builder(getActivity())
                .title(res.getString(R.string.analytics_dialog_title))
                .content(res.getString(R.string.analytics_summ))
                .checkBoxPrompt(res.getString(R.string.analytics_title), true, null)
                .positiveText(R.string.dialog_continue)
                .onPositive((dialog, which) -> {
                    LowkeyAnkiDroidApp.getSharedPrefs(getContext()).edit()
                            .putBoolean(UsageAnalytics.ANALYTICS_OPTIN_KEY, dialog.isPromptCheckBoxChecked())
                            .apply();
                    ((DeckPicker) getActivity()).dismissAllDialogFragments();
                })
                .cancelable(true)
                .cancelListener(dialog -> ((DeckPicker) getActivity()).dismissAllDialogFragments())
                .show();
    }
}
