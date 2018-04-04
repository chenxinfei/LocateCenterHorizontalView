##LocateCenterHorizontalView使用方法
###一、参考效果


插入gif代码：
![gif5.gif](https://raw.githubusercontent.com/chenxinfei/LocateCenterHorizontalView/master/gif/gif5.gif)

###一、特点
  1.使用方式和Recyclerview基本一样，非常易用。
  2.当滑动停止的时候默认选中中间的item，字体变大，变色，底部Recyclerview更着变到当前选中的position
  3.支持手动控制选中任意item

###二、使用方式
  1.主要代码
  基本使用 和Recyclerview一模一样，唯一不同的是在继承RecyclerView.Adapter时要实现 AutoLocateHorizontalView.IAutoLocateHorizontalView 接口。如下所示：

    public class IndexZhouTextAdapter extends RecyclerView.Adapter<IndexZhouTextAdapter.AgeViewHolder>
        implements LocateCenterHorizontalView.IAutoLocateHorizontalView
在该接口中我们要实现两个方法：

    @Override
    public void onViewSelected(boolean isSelected, int pos, RecyclerView.ViewHolder holder, int itemWidth) {
        if (isSelected) {
            ((AgeViewHolder) holder).name.setTextSize(20);
            ((AgeViewHolder) holder).name.setTextColor(Color.parseColor("#FD7422"));
        } else {
            ((AgeViewHolder) holder).name.setTextSize(14);
            ((AgeViewHolder) holder).name.setTextColor(Color.parseColor("#999999"));
        }
    }
其中getItemView()方法是用来获取列表中的每一个item的布局，可以在onCreateViewHolder()方法中获取如：

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(context).inflate(R.layout.item_bar,parent,false);
        this.itemView = itemView;
        ViewHolder holder = new ViewHolder(itemView);
        return holder;
    }
LocateCenterHorizontalView滑动监听停止底部Recyclerview跟着改变

    zhouText.setOnSelectedPositionChangedListener(new LocateCenterHorizontalView.OnSelectedPositionChangedListener() {
            @Override
            public void selectedPositionChanged(int pos) {
                if (zhouTextAdapter.getData().size() > 0) {
                    int i = pos % zhouTextAdapter.getData().size();
                    moveToPosition(i);
                }
            }
        });
底部Recyclerview自动滑动到LocateCenterHorizontalView选中的position

    private void moveToPosition(int n) {
        mIndex = n;
        //先从RecyclerView的LayoutManager中获取第一项和最后一项的Position
        LinearLayoutManager layoutManager = (LinearLayoutManager) zhouImage.getLayoutManager();
        int firstItem = layoutManager.findFirstVisibleItemPosition();
        int lastItem = layoutManager.findLastVisibleItemPosition();
        //然后区分情况
        if (n <= firstItem) {
            //当要置顶的项在当前显示的第一个项的前面时
            zhouImage.smoothScrollToPosition(n);
        } else if (n <= lastItem) {
            //当要置顶的项已经在屏幕上显示时
            int top = zhouImage.getChildAt(n - firstItem).getTop();
            zhouImage.scrollBy(0, top);
        } else {
            //当要置顶的项在当前显示的最后一项的后面时
            zhouImage.scrollToPosition(n);
            //这里这个变量是用在RecyclerView滚动监听里面的
            move = true;
        }

    }
而 onViewSelected()方法中可以根据item是否被选中设置选中效果，比如选中时改变颜色，或者加粗等。

##2. 常用方法

setInitPos(int pos) 设置最开始时选中的位置。

setItemCount(int itemCount) 设置每屏显示多少个item，item越多，则每个item的宽度相应的变小。

setOnSelectedPositionChangedListener(OnSelectedPositionChangedListener listener) 设置位置改变时的监听器，当滑动或增加、删除数据时，只要选中的位置发生了改变就会回调。注意，当增加和删除时，有可能选中的位置不变，但这个时候选中的数据不再是原来的数据也会回调。


三、参考效果  https://www.jianshu.com/p/5f38d63c0076



 ```
License
=======

    Copyright 2018 Chen xinfei

    Licensed under the Apache License, Version 1.0 (the "License");
    you may not use this file except in compliance with the License.
    You may obtain a copy of the License at

       http://www.apache.org/licenses/LICENSE-2.0

    Unless required by applicable law or agreed to in writing, software
    distributed under the License is distributed on an "AS IS" BASIS,
    WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
    See the License for the specific language governing permissions and
    limitations under the License.






