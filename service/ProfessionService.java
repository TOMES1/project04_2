package project04_2.service;

import project04_2.data.ProfessionData;
import project04_2.model.Profession;
import project04_2.util.ScannerUtil;

/**
 * 跟职业相关的功能
 */
public class ProfessionService {

    /**
     * 选择职业
     *
     * @return 返回玩家选中的职业
     */
    public static Profession choiceProfession() {

        while (true) {
            System.out.println("有这些职业供您选择：");

            // 输出游戏数据ProfessionData中存在的职业信息
            for (int i = 0; i < ProfessionData.professions.length; i++) {
                // 让每个职业以“序号.职业名”的方式输出显示
                System.out.println((i + 1) + "." + ProfessionData.professions[i].getProfessionName());
            }

            Profession profession = null;

            // 让玩家根据数字做出选择，输入有误可以重复选择
            System.out.println("请做出您的选择：");
            String s = ScannerUtil.scanner.next();
            if (s.length() > 1 || s.charAt(0) > 50 || s.charAt(0) < 49) {
                System.out.println("输入错误！请重新输入！");
            } else{
                profession = ProfessionData.professions[Integer.valueOf(s)-1];
                return profession;
            }
        }

    }


}
