package project04_2.view;

import project04_2.data.ProfessionData;
import project04_2.model.Enemy;
import project04_2.model.Place;
import project04_2.model.Player;
import project04_2.model.Profession;
import project04_2.service.EnemyService;
import project04_2.service.PlaceService;
import project04_2.service.PlayerService;
import project04_2.service.ProfessionService;
import project04_2.service.npc.NpcDoctorService;
import project04_2.service.npc.NpcMasterService;
import project04_2.service.npc.NpcService;
import project04_2.util.ScannerUtil;

import java.util.Scanner;

/**
 * 故事
 */
public class Story {
    // 故事中的玩家
    Player player;

    // 故事中的场景
    Place place;

    NpcMasterService npcMasterService = new NpcMasterService();
    NpcDoctorService npcDoctorService = new NpcDoctorService();
    PlayerService playerService = new PlayerService();
    PlaceService placeService = new PlaceService();
    EnemyService enemyService = new EnemyService();

    /**
     * 开始游戏
     */
    public void start() {

        // 初始化角色
        //初始化玩家
        player = PlayerService.initPlayer();
        //System.out.println("欢迎来到大型文字角色扮演游戏《伏魔录》！");
        // System.out.println("我该如何称呼您？");
        // String name=ScannerUtil.scanner.nextLine();
        System.out.println("角色初始化完毕，您的伏魔之旅即将开始！");
        System.out.println("**********************************");
        menu();
        // hunt();
    }

    /**
     * 提供游戏行动菜单供玩家选择
     */
    private void menu() {
        int i = 0;
        boolean flag = true;

        while (flag) {
            i++;
            System.out.println("请问现在您需要打算做什么呢^_^");
            System.out.println("1.打怪");
            System.out.println("2.治疗");
            System.out.println("3.学习技能");
            System.out.println("4.退出游戏");
            System.out.println("5.人物属性");
            public static void main(String[] args){
                int num = sc1.next();
                if (0 <= num && num < 3) {
                    player.setValue(num);
                    break;
                } else {
                    System.out.println("输入有误，请重新输入");
                    continue;
                }
            }
            int choi = ScannerUtil.scanner.nextInt();
            switch (choi) {
                case 1: {
                    hunt();
                    break;
                }
                case 2: {
                    healing();
                    break;
                }
                case 3: {
                    learnSkill();
                    break;
                }
                case 4: {
                    flag = false;
                    break;
                }
                case 5: {
                    System.out.println(player.toString());
                    break;
                }
                default: {
                    System.out.println("您的输入有误，请重新输入");
                }
            }

        }
    }

    /**
     * 打怪
     */
    private void hunt() {


        // 选择要去的场景
        place = placeService.choicePlace();


        // 选择要打的敌人
        Enemy enemy = enemyService.choiceEnemy(place);

        // 玩家与敌人进行战斗
        playerService.fight(player, enemy);


    }

    /**
     * 疗伤
     */
    private void healing() {
        npcDoctorService.helpPlayer(player);


    }

    /**
     * 学习技能
     */
    private void learnSkill() {
        npcMasterService.helpPlayer(player);

    }
}
