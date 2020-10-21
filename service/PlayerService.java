package project04_2.service;

import project04_2.model.Enemy;
import project04_2.model.Player;
import project04_2.model.Skill;
import project04_2.util.ScannerUtil;
//import com.dell.yangzhou.project04.view.FightMenu;

import java.util.Scanner;

public class PlayerService {
    /**
     * 初始化玩家角色信息
     *
     * @return 返回实例化的player对象
     */
    public static Player initPlayer() {

        // 1.输出欢迎信息
        System.out.println("*****************************************");
        System.out.println("欢迎来到大型文字角色扮演游戏《伏魔录》");
        System.out.println("我该如何称呼您？");
        String playerName = ScannerUtil.scanner.nextLine();
        // 2.输出角色名
        System.out.println("你设置的名字为 : " + playerName);

        // 3.调用构造函数初始化角色
        Player player = new Player(playerName, 1, 0, 5000, 5,
                2, 100, 100, 100, 100);
        player.setProfession(ProfessionService.choiceProfession()); //选择职业

        // 4.输出初始化结果信息
        System.out.println(player);


        return player;
    }

    /**
     * 玩家跟敌人战斗
     *
     * @param player 参与战斗的玩家
     * @param enemy  挑战的敌人
     */
    public static void fight(Player player, Enemy enemy) {
        int y = 0;
        int hp = enemy.getHp();
        System.out.println("遭遇了敌人：" + enemy.getEnemyName());
        // 1.使用do-while循环，持续进行战斗，到有一方血量扣完为止
        while (player.getHp() > 0 && enemy.getHp() > 0){
            // 2.玩家选择行为
            int x = action(player);
            if (x == 1)
                y = attack(player);
            else if (x == 2) {
                if (player.getSkills().size() == 0){
                    System.out.println("你没有技能");
                    continue;
                }
                y = userSkill(player);
                if (y==0)
                    continue;
            }
            // 3.敌人生命值进行扣减
            enemy.setHp(enemy.getHp() - y + enemy.getDefense());
            System.out.println("您对"+enemy.getEnemyName()+"攻击，造成" + (y-enemy.getDefense())+ "伤害");
            player.setHp(player.getHp() - enemy.getAttack()+player.getDefense());
            // 4.对于敌人是否战败进行判断
            if (enemy.getHp() <= 0) {
                System.out.println("您获得了胜利！");
                player.setHp(player.getHp() + enemy.getAttack()-player.getDefense());
                victory(player, enemy);
                break;
            }
            else if (player.getHp() <= 0) {
                System.out.println(enemy.getEnemyName() + "使用了普通攻击，造成" + (enemy.getAttack()-player.getDefense()) + "伤害");
                System.out.println("您已战败，落荒而逃");
                player.setHp(1);
                break;
            }
            else {
                System.out.println(enemy.getEnemyName() + "使用了普通攻击，造成" + (enemy.getAttack()-player.getDefense()) + "伤害");
                System.out.println(player.getPlayerName() + "剩余血量：" + player.getHp());
                System.out.println("敌人剩余血量：" + enemy.getHp());
            }
        }

        enemy.setHp(hp);
    }

    /**
     * 玩家选择战斗中的行为,攻击或者释放技能
     *
     * @param player 参与战斗的玩家
     * @return 对敌人造成伤害
     */
    private static int action(Player player) {
        String s;
        // 输出可选行动
        while (true) {
            System.out.println("当前回合，您可采取如下行动：");
            System.out.println("1.攻击");
            System.out.println("2.施法");
            s = ScannerUtil.scanner.next();
            if (s.length() > 1 || s.charAt(0) > 50 || s.charAt(0) < 49) {
                System.out.println("输入错误！请重新输入！");
            } else
                break;
        }
        return Integer.valueOf(s);
    }

    /**
     * 玩家对敌人进行普通攻击
     *
     * @param player 参与战斗的玩家
     * @return 攻击产生的伤害
     */
    private static int attack(Player player) {
        return player.getAttack();
    }

    /**
     * 战斗胜利后玩家获得金钱奖励，以及经验
     * 升级玩家可以提升属性
     *
     * @param player 战斗胜利的玩家
     * @param enemy  被击败的敌人
     */
    private static void victory(Player player, Enemy enemy) {
        player.setMana(player.getMoney() + enemy.getMoney());
        player.setExp(player.getExp() + enemy.getExp());
        if (player.getExp() >= 100)
            levelUp(player);
    }

    /**
     * 玩家升级
     *
     * @param player 要升级的玩家
     */
    private static void levelUp(Player player) {
        player.setExp(player.getExp() - 100);
        player.setLevel(player.getLevel() + 1);
        player.setAttack(player.getAttack() + player.getProfession().getAttackGrow());
        player.setDefense(player.getDefense() + player.getProfession().getDefenseGrow());
        player.setMaxHp(player.getMaxHp() + player.getProfession().getHpGrow());
        player.setMaxMana(player.getMana() + player.getProfession().getManaGrow());
    }

    /**
     * 玩家对敌人释放技能进行攻击
     *
     * @param player 参与战斗的玩家
     * @return 技能产生的伤害
     */
    private static int userSkill(Player player) {
        while (true) {
            System.out.println("技能：");
            for (int i = 0; i < player.getSkills().size(); i++) {
                System.out.println(i + 1 + "." + player.getSkills().get(i).getSkillName());
            }
            System.out.println("请做出你的选择：");
            System.out.println("若选择使用技能，请输入技能数字：");
            String s = ScannerUtil.scanner.next();
            if (s.length() > 1 || s.charAt(0) > (48 + player.getSkills().size()) || s.charAt(0) < 49) {
                System.out.println("输入错误！请重新输入！");
            }else if(player.getMana()<player.getSkills().get(Integer.valueOf(s)-1).getMana()){
                System.out.println("蓝量不够！");
                return 0;
            } else{
                player.setMana(player.getMana()-player.getSkills().get(Integer.valueOf(s)-1).getMana());
                return player.getAttack()*player.getSkills().get(Integer.valueOf(s)-1).getAttackAddition();
            }
        }
    }

}
