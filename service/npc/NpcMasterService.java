package project04_2.service.npc;

import project04_2.data.NpcData;
import project04_2.data.ProfessionData;
import project04_2.data.SkillData;
import project04_2.model.Player;
import project04_2.model.Skill;
import project04_2.util.ScannerUtil;

import java.util.List;
import java.util.InputMismatchException;
import java.util.Iterator;

/**
 * 包含技能导师npc具有的方法
 */
public class NpcMasterService extends AbstractNpcService {
    /**
     * 构造方法，在实例化技能导师npc时生效
     */
    public NpcMasterService() {

        // 给从父类继承的npc对象进行赋值
        this.npc = NpcData.npcMaster;

    }

    /**
     * 支付金币学习技能
     * @param player
     */
    @Override
    public void helpPlayer(Player player) {
        System.out.println(npc.getNpcName()+":"+npc.getDialogue());
        if(player.getProfession().getProfessionName().equals(ProfessionData.professions[0].getProfessionName())){
            int i = 1;
            for (Iterator it = SkillData.swordmanSkills.iterator(); it.hasNext();) {
                Skill skill = (Skill) it.next();
                System.out.println(i+":"+skill.getSkillName());
                i++;

            }
            choiceSkill(player, SkillData.swordmanSkills);

        }else{
            int i = 1;
            for (Iterator it = SkillData.warlockSkills.iterator(); it.hasNext();) {
                Skill skill = (Skill) it.next();
                System.out.println(i+":"+skill.getSkillName());
                i++;

            }
            choiceSkill(player, SkillData.warlockSkills);
        }
    }

    public void choiceSkill(Player player, List<Skill> list){
        System.out.println("请选择：");
        int num = -1;
        while(true){
            try{
                num = ScannerUtil.scanner.nextInt();
                if(num >= 1 && num <= list.size()){
                    boolean flag = false;
                    for (Iterator it = player.getSkills().iterator(); it.hasNext();) {
                        Skill skill = (Skill) it.next();
                        if (skill.getSkillName().equals(list.get(num-1).getSkillName())){
                            flag = true;
                        }
                    }
                    if(flag){
                        System.out.println("已经学过该技能了！");
                        return;
                    }
                    break;
                }else{
                    continue;
                }
            }catch (InputMismatchException e){
                ScannerUtil.scanner.nextLine();
                System.out.println("看看你都干了什么好事！" + e);
                System.out.println("在输入一次.");
            }
        }
        System.out.println("学习"+list.get(num-1).getSkillName()+"需要金钱："+list.get(num-1).getSkillMoney()
                +"\t确定支付吗？(y确定，n否定)");
        String str = "";
        ScannerUtil.scanner.nextLine();
        while(true){
            str = ScannerUtil.scanner.nextLine().trim();
            if(str.equalsIgnoreCase("y")){
                if(player.getMoney()>=list.get(num-1).getSkillMoney()){

                    player.setMoney(player.getMoney()-list.get(num-1).getSkillMoney());
                    List<Skill> list2 = player.getSkills();
                    list2.add(list.get(num-1));
                    player.setSkills(list2);

                    System.out.println("学习完成");
                    break;
                }else {
                    System.out.println("金钱不足。");
                    break;
                }

            }else if(str.equalsIgnoreCase("n")){
                System.out.println("阿巴啊吧啊吧，有缘再会。");
                break;
            }else{
                System.out.println("既不是y也不是n，我听不懂，请再输入一遍。");
            }
        }
    }
}
