package Liuyanmod.cards.liuyan;

import Liuyanmod.powers.GejuPower;
import basemod.abstracts.CustomCard;
import com.megacrit.cardcrawl.actions.common.GainBlockAction;
import com.megacrit.cardcrawl.actions.common.GainEnergyAction;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

import static Liuyanmod.characters.MyCharacter.PlayerColorEnum.EXAMPLE_GREEN;

public class LongYin extends CustomCard {
    public static final String ID = "Liuyanmod:LongYin";
    private static final CardStrings strings = CardCrawlGame.languagePack.getCardStrings(ID);
    public static final String NAME = strings.NAME;
    public static final String DESCRIPTION = strings.DESCRIPTION;
    private static final String IMG = "Liuyan/img/cards/Longyin_attack.png";

    private static final int COST = 2;
    private static final int DAMAGE = 15;
    private static final int UPGRADE_DAMAGE = 4;
    private static final int BLOCK_GAIN = 10;

    public LongYin() {
        super(ID, NAME, IMG, COST, DESCRIPTION,
                CardType.ATTACK,
                EXAMPLE_GREEN,
                CardRarity.UNCOMMON,
                CardTarget.ENEMY);

        this.baseDamage = DAMAGE;
        this.baseMagicNumber=BLOCK_GAIN;
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        // 基础伤害效果
        CardCrawlGame.sound.play("LONGDAN"); // 可调节音量，0.9f 是常用默认
        addToBot(new DamageAction(m, new DamageInfo(p, damage, DamageInfo.DamageType.NORMAL),
                com.megacrit.cardcrawl.actions.AbstractGameAction.AttackEffect.SLASH_HEAVY));

        // 检查割据状态并回复能量
        if (p.hasPower(GejuPower.POWER_ID)) {
            addToBot(new GainBlockAction(p, p, baseMagicNumber));
        }
    }

    @Override
    public void upgrade() {
        if (!upgraded) {
            upgradeName();
            upgradeDamage(UPGRADE_DAMAGE);
            this.upgradeMagicNumber(3);
            initializeDescription();
        }
    }

    @Override
    public AbstractCard makeCopy() {
        return new LongYin();
    }
}
