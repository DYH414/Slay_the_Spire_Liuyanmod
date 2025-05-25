package Liuyanmod.cards.liuyan;

import basemod.abstracts.CustomCard;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

import static Liuyanmod.characters.MyCharacter.PlayerColorEnum.EXAMPLE_GREEN;

public class DieLang extends CustomCard {
    public static final String ID = "Liuyanmod:DieLang";
    private static final CardStrings strings = CardCrawlGame.languagePack.getCardStrings(ID);
    public static final String NAME = strings.NAME;
    public static final String DESCRIPTION = strings.DESCRIPTION;
    private static final String IMG = "Liuyan/img/cards/Dielang_attack.jpg";

    private static final int COST = 0;
    private static final int BASE_DAMAGE = 4;
    private static final int DAMAGE_INCREMENT = 3;
    private static final int UPGRADE_BASE_DAMAGE = 6;
    private static final int UPGRADE_DAMAGE_INCREMENT = 4;

    // 使用静态变量跟踪全局使用次数
    public static int totalTimesPlayedThisTurn = 0;

    public DieLang() {
        super(ID, NAME, IMG, COST, DESCRIPTION,
                CardType.ATTACK,
                EXAMPLE_GREEN,
                CardRarity.COMMON,
                CardTarget.ENEMY);
        this.baseMagicNumber = DAMAGE_INCREMENT;
        this.magicNumber = baseMagicNumber;
        this.baseDamage = BASE_DAMAGE;
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        // 计算伤害
        int finalDamage = this.damage + (totalTimesPlayedThisTurn * magicNumber);

        // 造成伤害
        addToBot(new DamageAction(m, new DamageInfo(p, finalDamage, DamageInfo.DamageType.NORMAL),
                com.megacrit.cardcrawl.actions.AbstractGameAction.AttackEffect.SLASH_HORIZONTAL));

        // 增加全局使用次数
        totalTimesPlayedThisTurn++;

        // 更新描述
        initializeDescription();
    }

    @Override
    public void atTurnStart() {
        // 每回合开始时重置全局计数器
        totalTimesPlayedThisTurn = 0;

        // 更新描述
        initializeDescription();
    }

    @Override
    public void upgrade() {
        if (!upgraded) {
            upgradeName();
            upgradeDamage(UPGRADE_BASE_DAMAGE - BASE_DAMAGE);
            upgradeMagicNumber(UPGRADE_DAMAGE_INCREMENT - DAMAGE_INCREMENT);
            initializeDescription();
        }
    }

    @Override
    public AbstractCard makeCopy() {
        // 直接返回新实例，不再需要复制状态
        return new DieLang();
    }
}
