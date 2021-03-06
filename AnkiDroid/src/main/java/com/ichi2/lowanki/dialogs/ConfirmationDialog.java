
package com.ichi2.lowanki.dialogs;

import android.content.res.Resources;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.DialogFragment;

import com.afollestad.materialdialogs.MaterialDialog;
import com.ichi2.lowanki.R;

    /**
     * This is a reusable convenience class which makes it easy to show a confirmation dialog as a DialogFragment.
     * Create a new instance, call setArgs(...), setConfirm(), and setCancel() then show it via the fragment manager as usual.
     */
    public class ConfirmationDialog extends DialogFragment {
        private Runnable mConfirm = () -> { }; // Do nothing by default
        private Runnable mCancel = () -> { };  // Do nothing by default

        public void setArgs(String message) {
            setArgs("" , message);
        }

        public void setArgs(String title, String message) {
            Bundle args = new Bundle();
            args.putString("message", message);
            args.putString("title", title);
            setArguments(args);
        }

        public void setConfirm(Runnable confirm) {
            mConfirm = confirm;
        }

        public void setCancel(Runnable cancel) {
            mCancel = cancel;
        }

        @NonNull
        @Override
        public MaterialDialog onCreateDialog(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            Resources res = getActivity().getResources();
            String title = getArguments().getString("title");
            return new MaterialDialog.Builder(getActivity())
                .title("".equals(title) ? res.getString(R.string.app_name) : title)
                    .content(getArguments().getString("message"))
                    .positiveText(R.string.dialog_ok)
                    .negativeText(R.string.dialog_cancel)
                    .onPositive((dialog, which) -> mConfirm.run())
                    .onNegative((dialog, which) -> mCancel.run())
                    .show();
        }
    }
