package project04_2.service.npc;

import project04_2.data.NpcData;
import project04_2.model.Player;
import project04_2.util.ScannerUtil;
/**
 * 包含回复导师npc的具有的方法
 */
public class NpcDoctorService extends AbstractNpcService {
    /**
     * 构造方法，在实例化医师npc时生效
     */
    public NpcDoctorService() {
        // 给从父类继承的npc对象进行赋值
        this.npc = NpcData.npcDoctor;

    }

    /**
     * 支付金币进行治疗
     * @param player
     */
    @Override
    public void helpPlayer(Player player) {
        System.out.println(npc.getNpcName()+":"+npc.getDialogue());
        System.out.println("确定接收治疗?(键入y确定，n取消)");
        String str = "";
        ScannerUtil.scanner.nextLine();
        while(true){
            str = ScannerUtil.scanner.nextLine().trim();
            if(str.equalsIgnoreCase("y")){
                if(player.getHp()==player.getMaxHp() && player.getMana()==player.getMaxMana()){
                    System.out.println("生命值，法力值都是满的，请少侠挨了打再来。");
                    break;
                }
                player.setHp(player.getMaxHp());
                player.setMana(player.getMaxMana());
                System.out.println("当前的血量为："+player.getHp()+"当前的法力值为"+player.getMana());
                break;
            }else if(str.equalsIgnoreCase("n")){
                System.out.println("有缘再会。");
                break;
            }else{
                System.out.println("既不是y也不是n，我听不懂，请再输入一遍。");
            }
        }
    }
}

