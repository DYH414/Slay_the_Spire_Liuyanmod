package Liuyanmod;

import Liuyanmod.cards.liuyan.*;
import Liuyanmod.characters.MyCharacter;
import Liuyanmod.relics.Fantu;
import Liuyanmod.relics.GejuZhiYin;
import Liuyanmod.relics.Tusheyugui;
import basemod.BaseMod;
import basemod.helpers.RelicType;
import basemod.interfaces.*;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.evacipated.cardcrawl.modthespire.lib.SpireInitializer;
import com.google.gson.Gson;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.localization.*;

import java.nio.charset.StandardCharsets;

import static Liuyanmod.characters.MyCharacter.PlayerColorEnum.EXAMPLE_GREEN;
import static Liuyanmod.characters.MyCharacter.PlayerColorEnum.MY_CHARACTER;
import static com.megacrit.cardcrawl.core.Settings.language;

@SpireInitializer
public class ExampleMod implements  AddAudioSubscriber, EditKeywordsSubscriber,EditCardsSubscriber, EditStringsSubscriber, EditCharactersSubscriber, EditRelicsSubscriber { // 实现接口

    private static final String MY_CHARACTER_BUTTON = "Liuyan/img/cardui/button.png";
    // 人物选择界面的立绘
    private static final String MY_CHARACTER_PORTRAIT = "Liuyan/img/BK/Liuyanbg.png";
    // 攻击牌的背景（小尺寸）
    private static final String BG_ATTACK_512 = "Liuyan/img/cardui/bg_attack_512.png";
    // 能力牌的背景（小尺寸）
    private static final String BG_POWER_512 = "Liuyan/img/cardui/bg_power_512.png";
    // 技能牌的背景（小尺寸）
    private static final String BG_SKILL_512 = "Liuyan/img/cardui/bg_skill_512.png";
    // 在卡牌和遗物描述中的能量图标
    private static final String SMALL_ORB = "Liuyan/img/cardui/energy_relic.png";
    // 攻击牌的背景（大尺寸）
    private static final String BG_ATTACK_1024 = "Liuyan/img/cardui/bg_attack_1024.png";
    // 能力牌的背景（大尺寸）
    private static final String BG_POWER_1024 = "Liuyan/img/cardui/bg_power_1024.png";
    // 技能牌的背景（大尺寸）
    private static final String BG_SKILL_1024 = "Liuyan/img/cardui/bg_skill_1024.png";
    // 在卡牌预览界面的能量图标
    private static final String BIG_ORB = "Liuyan/img/cardui/energy_card.png";
    // 小尺寸的能量图标（战斗中，牌堆预览）
    private static final String ENEYGY_ORB = "Liuyan/img/cardui/energy_card.png";
    public static final Color MY_COLOR = new Color(79.0F / 255.0F, 185.0F / 255.0F, 9.0F / 255.0F, 1.0F);
    public ExampleMod() {
        BaseMod.subscribe(this); // 告诉basemod你要订阅事件
        // 这里注册颜色
        BaseMod.addColor(EXAMPLE_GREEN, MY_COLOR, MY_COLOR, MY_COLOR, MY_COLOR, MY_COLOR, MY_COLOR, MY_COLOR,BG_ATTACK_512,BG_SKILL_512,BG_POWER_512,ENEYGY_ORB,BG_ATTACK_1024,BG_SKILL_1024,BG_POWER_1024,BIG_ORB,SMALL_ORB);
    }

    public static void initialize() {
        new ExampleMod();
    }


    @Override
    public void receiveAddAudio() {
        BaseMod.addAudio("TUSHEYUGUI_DRAW_1", "Liuyan/sounds/TUSHE_1.ogg");
        BaseMod.addAudio("TUSHEYUGUI_DRAW_2", "Liuyan/sounds/TUSHE_2.ogg");
        BaseMod.addAudio("LIMU_1", "Liuyan/sounds/LIMU_1.ogg");
        BaseMod.addAudio("LIMU_2", "Liuyan/sounds/LIMU_2.ogg");
        BaseMod.addAudio("LONGXING", "Liuyan/sounds/longxing.ogg");
        BaseMod.addAudio("DIE", "Liuyan/sounds/die_406.ogg");
        BaseMod.addAudio("LONGDAN", "Liuyan/sounds/SKILL_10802_longdan_1.ogg");
        BaseMod.addAudio("POJUN", "Liuyan/sounds/Pojun.ogg");
        BaseMod.addAudio("Jiu", "Liuyan/sounds/WENJIU.ogg");
        BaseMod.addAudio("Dandaofuhui", "Liuyan/sounds/Dandaofuhui.ogg");
        BaseMod.addAudio("KONGCHENG", "Liuyan/sounds/Qinsheng.ogg");
        BaseMod.addAudio("Wuxiekeji", "Liuyan/sounds/Wuxiekeji.ogg");
        BaseMod.addAudio("MOUDING", "Liuyan/sounds/SKILL_402_guanxing_2.ogg");
        BaseMod.addAudio("ZHUOSHAO", "Liuyan/sounds/Zhuoshao.ogg");

    }
    // 当basemod开始注册mod卡牌时，便会调用这个函数
    @Override
    public void receiveEditCards() {
        // TODO 这里写添加你卡牌的代码
        BaseMod.addCard(new Strike());
        BaseMod.addCard(new Wanjianqifa());
        BaseMod.addCard(new Defend());
        BaseMod.addCard(new Wuzhongshengyou());
        BaseMod.addCard(new Tao());
        BaseMod.addCard(new Jiu());
        BaseMod.addCard(new Shan());
        BaseMod.addCard(new Qinggangjian());
        BaseMod.addCard(new Nanmanruqin());
        BaseMod.addCard(new Limu());
        BaseMod.addCard(new Zhoumuweiyi());
        BaseMod.addCard(new Zhangbashemao());
        BaseMod.addCard(new Baozheng());
        BaseMod.addCard(new Yewang());
        BaseMod.addCard(new Beishui());
        BaseMod.addCard(new Quanmouzhuxin());
        BaseMod.addCard(new Tanlu());
        BaseMod.addCard(new Wangshizhinu());
        BaseMod.addCard(new Shan());
        BaseMod.addCard(new Sha());
        BaseMod.addCard(new Qingjunce());
        BaseMod.addCard(new Wuxiekeji());
        BaseMod.addCard(new Xuezhao());
        BaseMod.addCard(new Baguazhen());
        BaseMod.addCard(new Chitu());
        BaseMod.addCard(new Dilu());
        BaseMod.addCard((new Guohechaiqiao()));
        BaseMod.addCard(new Shunshouqianyang());
        BaseMod.addCard(new Qinglongyanyuedao());
        BaseMod.addCard(new Wugufengdeng());
        BaseMod.addCard(new Renwangdun());
        BaseMod.addCard(new Kongcheng());
        BaseMod.addCard(new Shandian());
        BaseMod.addCard(new Qibuchengshi());
        BaseMod.addCard(new Qilingong());
        BaseMod.addCard(new WolongChushan());
        BaseMod.addCard(new SanguMaolu());
        BaseMod.addCard(new LongzhongDui());
        BaseMod.addCard(new Taoyuanjieyi());
        BaseMod.addCard(new Gengshou());
        BaseMod.addCard(new Dandaofuhui());
        BaseMod.addCard(new Minggu());
        BaseMod.addCard(new ZhugeLiannu());
        BaseMod.addCard(new Xunshidaifa());
        BaseMod.addCard(new WenjuzhanHuaxiong());
        BaseMod.addCard(new Huogong());
        BaseMod.addCard(new ZhuqueYushan());
        BaseMod.addCard(new Huoshaolianying());
        BaseMod.addCard(new Wuku());
        BaseMod.addCard(new Tengjia());
        BaseMod.addCard(new TiesuoLianhuan());
        BaseMod.addCard(new Gushou());
        BaseMod.addCard(new Huosha());
        BaseMod.addCard(new Chengshengzhuiji());
        BaseMod.addCard(new YushiWeifang());
        BaseMod.addCard(new GejuQiangxi());
        BaseMod.addCard(new ZhuzhouFenluan());
        BaseMod.addCard(new Jushi());
        BaseMod.addCard(new ShiruPozhu());
        BaseMod.addCard(new ZhuzhouFenluan());
        BaseMod.addCard(new QunxiongGeju());
        BaseMod.addCard(new LongYin());
        BaseMod.addCard(new PofuChenzhou());
        //BaseMod.addCard(new DieLang());
        BaseMod.addCard(new LeBuSiShu());
        BaseMod.addCard(new ZhuLuTianXia());
        BaseMod.addCard(new YinRan());
        BaseMod.addCard(new PoJun());
        BaseMod.addCard(new GuShouYiFang());
        BaseMod.addCard(new LimuMouding());
        BaseMod.addCard(new Chouliang());
        BaseMod.addCard(new YuboJiaofeng());
        BaseMod.addCard(new GuzhenDaidi());
    }

    @Override
    public void receiveEditCharacters() {
        // 向basemod注册人物
        BaseMod.addCharacter(new MyCharacter(CardCrawlGame.playerName), MY_CHARACTER_BUTTON, MY_CHARACTER_PORTRAIT, MY_CHARACTER);
    }

    @Override
    public void receiveEditRelics() {
        BaseMod.addRelic(new Tusheyugui(), RelicType.SHARED); // RelicType表示是所有角色都能拿到的遗物，还是一个角色的独有遗物
        BaseMod.addRelic(new GejuZhiYin(), RelicType.SHARED);
        BaseMod.addRelic(new Fantu(), RelicType.SHARED);
    }

    @Override
    public void receiveEditKeywords() {
        Gson gson = new Gson();
        String lang = "ENG";
        if (language == Settings.GameLanguage.ZHS) {
            lang = "ZHS";
        }

        String json = Gdx.files.internal("Liuyan/localization/"+lang+"/keywords.json")
                .readString(String.valueOf(StandardCharsets.UTF_8));
        Keyword[] keywords = gson.fromJson(json, Keyword[].class);
        if (keywords != null) {
            for (Keyword keyword : keywords) {
                // 这个id要全小写
                BaseMod.addKeyword("liuyanmod", keyword.NAMES[0], keyword.NAMES, keyword.DESCRIPTION);

            }
        }
    }

    @Override
    public void receiveEditStrings() {
        String lang;
        if (language == Settings.GameLanguage.ZHS) {
            lang = "ZHS"; // 如果语言设置为简体中文，则加载ZHS文件夹的资源
        } else {
            lang = "ENG"; // 如果没有相应语言的版本，默认加载英语
        }
        BaseMod.loadCustomStringsFile(RelicStrings.class, "Liuyan/localization/"+lang+"/relics.json"); // 加载相应语言的遗物本地化内容。
        BaseMod.loadCustomStringsFile(CardStrings.class, "Liuyan/localization/"+lang+"/cards.json"); // 加载相应语言的卡牌本地化内容。
        // 如果是中文，加载的就是"ExampleResources/localization/ZHS/cards.json"
        BaseMod.loadCustomStringsFile(PowerStrings.class, "Liuyan/localization/" + lang + "/powers.json");
        BaseMod.loadCustomStringsFile(CharacterStrings.class, "Liuyan/localization/"+lang+"/characters.json");

    }


}
