/*
  privacyIDEA Authenticator

  Authors: Nils Behlen <nils.behlen@netknights.it>

  Copyright (c) 2017-2019 NetKnights GmbH

  Licensed under the Apache License, Version 2.0 (the "License");
  you may not use this file except in compliance with the License.
  You may obtain a copy of the License at

  http://www.apache.org/licenses/LICENSE-2.0

  Unless required by applicable law or agreed to in writing, software
  distributed under the License is distributed on an "AS IS" BASIS,
  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
  See the License for the specific language governing permissions and
  limitations under the License.
*/

package it.netknights.piauthenticator.viewcontroller;

import android.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;

import it.netknights.piauthenticator.R;

public class AboutListAdapter extends BaseAdapter {

    private List<String> acknowledgements;

    @Override
    public View getView(final int position, View v, ViewGroup parent) {
        if (v == null) {
            final LayoutInflater inflater = LayoutInflater.from(parent.getContext());
            v = inflater.inflate(R.layout.about_row, parent, false);
        }
        final TextView about_title = v.findViewById(R.id.about_row_title);
        about_title.setText(acknowledgements.get(position));

        v.setOnClickListener(v1 -> {
            String text;
            if (position == 0) {
                text = readAcknowledgement(R.raw.apache2license, v1);
            } else if (position == 1) {
                text = readAcknowledgement(R.raw.otpauthenticator, v1);
            } else if (position == 2) {
                text = readAcknowledgement(R.raw.zxingandroidembedded, v1);
            } else if (position == 3) {
                text = readAcknowledgement(R.raw.androidcodesamples, v1);
            } else if (position == 4) {
                text = readAcknowledgement(R.raw.apache2license, v1);
            } else if (position == 5) {
                text = readAcknowledgement(R.raw.apache2license, v1);
            }
            else {
                return;
            }
            AlertDialog.Builder alert = new AlertDialog.Builder(v1.getContext());
            alert.setMessage(text);
            alert.show();
        });
        return v;
    }

    private String readAcknowledgement(int id, View v) {
        InputStream raw = v.getResources().openRawResource(id);

        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        int i;
        try {
            i = raw.read();
            while (i != -1) {
                byteArrayOutputStream.write(i);
                i = raw.read();
            }
            raw.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return byteArrayOutputStream.toString();
    }

    void setAcknowledgements(List<String> acknowledgements) {
        this.acknowledgements = acknowledgements;
    }

    private List<String> getAcknowledgements() {
        return acknowledgements;
    }

    @Override
    public int getCount() {
        if (getAcknowledgements() != null)
            return getAcknowledgements().size();
        return 0;
    }

    @Override
    public String getItem(int position) {
        return getAcknowledgements().get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }
}
