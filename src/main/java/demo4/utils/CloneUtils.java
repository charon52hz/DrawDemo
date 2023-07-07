package demo4.utils;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

public class CloneUtils {

    /**
     * 原型模式的序列化实现，深拷贝
     * 注意：基于序列化，如果单例中没有特殊处理，请不要将此方法用于单例模式的克隆，会导致出现多个实例。
     * 注意：此工具类用于学习，推荐使用spring提供的Bean拷贝方法。
     * @param source 原始对象
     * @param clazz 目的对象的类型，稍加改动就是反射实现。
     * @return 目的对象
     * @param <T>
     */
    public static <T> T clone(Object source, Class<T> clazz){
        //创建流对象
        ByteArrayOutputStream bos = null;
        ObjectOutputStream oos = null;
        ByteArrayInputStream bis = null;
        ObjectInputStream ois = null;
        try {
            //序列化
            bos = new ByteArrayOutputStream();
            oos = new ObjectOutputStream(bos);
            //当前这个对象以对象流的方式输出
            oos.writeObject(source);
            //反序列化
            bis = new ByteArrayInputStream(bos.toByteArray());
            ois = new ObjectInputStream(bis);
            return (T) ois.readObject();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        } finally {
            try {
                assert bos != null;
                bos.close();
                assert oos != null;
                oos.close();
                assert bis != null;
                bis.close();
                assert ois != null;
                ois.close();
            } catch (Exception e2) {
            }
        }
    }

    /**
     * 针对BufferedImage优化的对象拷贝
     * @param source
     * @return
     */
    public static BufferedImage copyImage(BufferedImage source){
        BufferedImage b = new BufferedImage(source.getWidth(), source.getHeight(), source.getType());
        Graphics g = b.getGraphics();
        g.drawImage(source, 0, 0, null);
        g.dispose();
        return b;
    }

}
