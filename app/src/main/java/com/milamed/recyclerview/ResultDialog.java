package com.milamed.recyclerview;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.DialogFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;

/**
 * Created by milamed on 14/03/17.
 */

public class ResultDialog extends DialogFragment {
    private static ResultDialog mResultDialog;
    private static ResultDialogListener mResultDialogListener;

    public static ResultDialog newInstance(ResultDialogListener resultDialogListener) {
        mResultDialog = new ResultDialog();
        mResultDialogListener = resultDialogListener;
        return mResultDialog;
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        Context context = getActivity();
        LayoutInflater layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        View view = layoutInflater.inflate(R.layout.add_item_dialog, null);
        final EditText titleEdit = (EditText) view.findViewById(R.id.title_edit);
        final EditText descriptionEdit = (EditText) view.findViewById(R.id.description_edit);
        AlertDialog.Builder alerteDialog = new AlertDialog.Builder(context);
        alerteDialog.setTitle("result").setView(view).setPositiveButton(android.R.string.ok, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                savePerform(titleEdit.getText().toString(), descriptionEdit.getText().toString());
            }
        }).setNegativeButton(android.R.string.cancel,null);
        return  alerteDialog.create();
    }

    private void savePerform(String title, String description) {
        if (mResultDialogListener != null) {
            if (!title.isEmpty() && !description.isEmpty()) {
                mResultDialogListener.onSave(title, description);
            }
        }
    }

    public interface ResultDialogListener {
         void onSave(String title, String description);

    }
}
