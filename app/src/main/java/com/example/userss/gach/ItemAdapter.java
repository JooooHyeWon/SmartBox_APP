package com.example.userss.gach;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.Drawable;
import android.os.AsyncTask;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.io.InputStream;
import java.util.ArrayList;

public class ItemAdapter extends BaseAdapter {

    /* 아이템을 세트로 담기 위한 어레이 */
    private ArrayList<Item> mItems = new ArrayList<>();

    @Override
    public int getCount() {
        return mItems.size();
    }

    @Override
    public Item getItem(int position) {
        return mItems.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        Context context = parent.getContext();

        /* 'listview_custom' Layout을 inflate하여 convertView 참조 획득 */
        if (convertView == null) {
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(R.layout.item_list, parent, false);
        }

        /* 'listview_custom'에 정의된 위젯에 대한 참조 획득 */
        TextView tv_rank = (TextView) convertView.findViewById(R.id.tv_rank);
        TextView tv_name = (TextView) convertView.findViewById(R.id.tv_name);
        TextView tv_favorite = (TextView) convertView.findViewById(R.id.tv_favorite);
        TextView tv_touch = (TextView) convertView.findViewById(R.id.tv_touch);
        TextView tv_shake = (TextView) convertView.findViewById(R.id.tv_shake);
        TextView tv_tryon = (TextView) convertView.findViewById(R.id.tv_tryon);

        /* 각 리스트에 뿌려줄 아이템을 받아오는데 mMyItem 재활용 */
        Item myItem = getItem(position);

        /* 각 위젯에 세팅된 아이템을 뿌려준다 */

        new DownloadImageTask((ImageView) convertView.findViewById(R.id.Iv_image))
                .execute(mItems.get(position).getList_photo());   // 사진 넣는 거 ^~^

        tv_rank.setText(position + 1 + "");    // 포지션 등수니까
        tv_name.setText(myItem.getList_name());
        tv_favorite.setText("호감도 : " + myItem.getList_favorite() + "점");
        tv_touch.setText(myItem.getList_touch() + "touch");
        tv_shake.setText(myItem.getList_shake() + "shake");
        tv_tryon.setText(myItem.getList_tryon() + "tryon");

        /* (위젯에 대한 이벤트리스너를 지정하고 싶다면 여기에 작성하면된다..)  */


        return convertView;
    }

    /* 아이템 데이터 추가를 위한 함수. 자신이 원하는대로 작성 */
    public void addItem(String list_name, int list_favorite, int list_touch, int list_shake, int list_tryon, String list_photo) {

        Item mItem = new Item(list_name, list_favorite, list_touch, list_shake, list_tryon, list_photo);

        /* MyItem에 아이템을 setting한다. */


        mItem.setList_name(list_name);
        mItem.setList_favorite(list_favorite);
        mItem.setList_touch(list_touch);
        mItem.setList_shake(list_shake);
        mItem.setList_tryon(list_tryon);
        mItem.setList_photo(list_photo);

        /* mItems에 MyItem을 추가한다. */
        mItems.add(mItem);

    }

    public void deleteItem(int i){
        mItems.remove(i);

    }

    // 사진 넣는 부분
    private class DownloadImageTask extends AsyncTask<String, Void, Bitmap> {
        ImageView bmImage;

        public DownloadImageTask(ImageView bmImage) {
            this.bmImage = bmImage;
        }

        protected Bitmap doInBackground(String... urls) {
            String urldisplay = urls[0];
            Bitmap mIcon11 = null;
            try {
                InputStream in = new java.net.URL(urldisplay).openStream();
                mIcon11 = BitmapFactory.decodeStream(in);
            } catch (Exception e) {
                Log.e("Error", e.getMessage());
                e.printStackTrace();
            }
            return mIcon11;
        }

        protected void onPostExecute(Bitmap result) {
            bmImage.setImageBitmap(result);
        }
    }
}