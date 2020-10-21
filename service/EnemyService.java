package project04_2.service;

import project04_2.model.Enemy;
import project04_2.model.Place;
import project04_2.util.ScannerUtil;

public class EnemyService {
    /**
     * 选择敌人
     * @param place
     * @return
     */
    public static Enemy choiceEnemy(Place place) {
        // 1.从传入的场景place中拿到场景中有的敌人
        System.out.println("您进入了"+place.getPlaceName());
        System.out.println(place.getPlaceName()+"有以下敌人：");
        // 2.显示全部场景中的敌人
        Enemy[] enemys = place.getEnemys();
        for (int i = 0; i < enemys.length; i++) {
            System.out.println(i+1+"."+enemys[i].getEnemyName());
        }
        System.out.println("请做出你的选择：");
        String s = ScannerUtil.scanner.next();
        if (s.length() > 1 || s.charAt(0) > 51 || s.charAt(0) < 49) {
            System.out.println("输入错误！请重新输入！");
        }
        return enemys[Integer.valueOf(s)-1];
    }
    public static int action(Enemy enemy) {
        return attack(enemy);
    }
    /**
     * 敌人普通攻击
     *
     * @param enemy
     *            对战中的敌人
     * @return 敌人普通攻击造成的伤害
     */
    private static int attack(Enemy enemy) {
        return enemy.getAttack();
    }
}
