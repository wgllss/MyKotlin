//package com.example.myapplication.base.binding_adapter;
//
//import android.net.Uri;
//import android.text.TextUtils;
//import android.widget.Toast;
//
//import androidx.databinding.BindingAdapter;
//
//import com.common.framework.utils.ScreenUtils;
//import com.facebook.drawee.backends.pipeline.Fresco;
//import com.facebook.drawee.interfaces.DraweeController;
//import com.facebook.drawee.view.SimpleDraweeView;
//import com.facebook.imagepipeline.common.ResizeOptions;
//import com.facebook.imagepipeline.request.ImageRequest;
//import com.facebook.imagepipeline.request.ImageRequestBuilder;
//
//public class ImageBindingAdapter {
//    @BindingAdapter(value = {"imgUrl","loadWidth"})
//    public static void setFrescoImg(SimpleDraweeView simpleDraweeView, String imgUrl, int loadWidth) {
//        try {
//            if (imgUrl==null|| TextUtils.isEmpty(imgUrl)||loadWidth==0){
//                return;
//            }
//            int size = ScreenUtils.dp2px(loadWidth);
//            ImageRequest request = ImageRequestBuilder.newBuilderWithSource(Uri.parse(imgUrl))
//                    .setResizeOptions(new ResizeOptions(size, size))
//                    .build();
//            DraweeController controller = Fresco.newDraweeControllerBuilder()
//                    .setImageRequest(request)
//                    .setOldController(simpleDraweeView.getController())
//                    .build();
//            simpleDraweeView.setController(controller);
//        } catch (Exception e) {
//            Toast.makeText(simpleDraweeView.getContext(), e.getMessage(), Toast.LENGTH_SHORT).show();
//        }
//
//    }
//}
