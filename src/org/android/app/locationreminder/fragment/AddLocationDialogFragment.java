package org.android.app.locationreminder.fragment;

import android.app.Activity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import org.android.app.locationreminder.R;
import org.android.app.locationreminder.dao.LocationsDaoService;
import org.android.app.locationreminder.dao.constant.ExtraKeys;
import org.android.app.locationreminder.dao.domain.Location;
import org.android.app.locationreminder.util.ApplicationUtil;
import roboguice.fragment.RoboDialogFragment;
import roboguice.inject.InjectView;

import javax.inject.Inject;
import java.util.Date;

/**
 * Date: 10.08.13
 */
public class AddLocationDialogFragment extends RoboDialogFragment implements View.OnClickListener{

    Location location;

    @InjectView(R.id.editLocationTitle)
    private EditText locationTitle;

    @InjectView(R.id.locationSave)
    private Button buttonSave;

    @InjectView(R.id.locationCancel)
    private Button buttonCancel;

    protected String defaultTitle;

    @Inject
    private LocationsDaoService locationsDaoService;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        this.location = getArguments().getParcelable(ExtraKeys.LOCATION);
        initializeDefaultTitle();
        return inflater.inflate(R.layout.location_dialog, container, false);
    }

    private void initializeDefaultTitle() {
        this.defaultTitle = new Date().toString();
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        getDialog().setTitle(R.string.enter_location_title);
        this.buttonSave.setOnClickListener(this);
        this.buttonCancel.setOnClickListener(this);
        this.locationTitle.setOnClickListener(this);
        setDefaultLocationTitle();
    }

    private void setDefaultLocationTitle() {
        this.locationTitle.append(this.defaultTitle);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.locationSave:
                saveLocation();break;
            case R.id.locationCancel:
                getTargetFragment().onActivityResult(getTargetRequestCode(), Activity.RESULT_CANCELED, null);
                dismiss();break;
            case R.id.editLocationTitle:
                clearTextIfDefault();
        }
    }

    private void saveLocation() {
        String locationTitle = this.locationTitle.getText().toString().trim();
        if (locationTitle.length() > 0) {
            this.location.setTitle(locationTitle);
            this.locationsDaoService.saveLocation(this.location);
            getTargetFragment().onActivityResult(getTargetRequestCode(), Activity.RESULT_OK, null);
            dismiss();
        }
        else {
            ApplicationUtil.showToast(R.string.disallow_empty_title, getActivity());
        }
    }

    private void clearTextIfDefault() {
        if (this.defaultTitle.equals(this.locationTitle.getText().toString())){
            this.locationTitle.setText("");
        }
    }
}