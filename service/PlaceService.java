package project04_2.service;

import project04_2.data.PlaceData;
import project04_2.model.Place;
import project04_2.util.ScannerUtil;

/**
 * 跟场景相关的方法
 */
public class PlaceService {
    /**
     * 选择场景
     *
     * @return 返回玩家选中的场景
     */
    public static Place choicePlace() {
        while (true) {
            System.out.println("有这些场景可供您选择：");

            // 1.输出游戏场景数据PlaceData中存在的场景信息
            for (int i = 0; i < PlaceData.places.length; i++) {
                System.out.println(i + 1 + "." + PlaceData.places[i].getPlaceName());
            }

            // 2.定义一个场景对象以便后面返回
            Place place = null;

            // 3.让玩家根据数字做出选择，输入有误可以重复选择
            System.out.println("请做出你的选择：");
            String s = ScannerUtil.scanner.next();
            if (s.length() > 1 || s.charAt(0) > 51 || s.charAt(0) < 49) {
                System.out.println("输入错误！请重新输入！");
            } else {
                // 4.输出场景信息
                place = PlaceData.places[Integer.valueOf(s)-1];
                return place;
            }
        }
    }
}
