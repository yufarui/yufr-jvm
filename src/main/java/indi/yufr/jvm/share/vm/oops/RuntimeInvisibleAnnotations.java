package indi.yufr.jvm.share.vm.oops;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

/**
 * Created By ziya
 * QQ: 3039277701
 * 2021/4/5
 */
@Data
public class RuntimeInvisibleAnnotations extends AttributeInfo {

    private int annotationsNum;
    private List<Annotation> annotations = new ArrayList<>();

    private String attrName;

}
