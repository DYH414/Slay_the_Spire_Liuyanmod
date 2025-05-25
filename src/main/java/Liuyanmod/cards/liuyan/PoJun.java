package Liuyanmod.cards.liuyan;

import Liuyanmod.powers.LimuPower;
import basemod.abstracts.CustomCard;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.LoseStrengthPower;
import com.megacrit.cardcrawl.powers.StrengthPower;
import com.megacrit.cardcrawl.powers.VulnerablePower;

import static Liuyanmod.characters.MyCharacter.PlayerColorEnum.EXAMPLE_GREEN;


public class PoJun extends CustomCard {
    public static final String ID = "Liuyanmod:PoJun";
    private static final CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings(ID);
    public static final String NAME = cardStrings.NAME;
    public static final String DESCRIPTION = cardStrings.DESCRIPTION;
    private static final String IMG_PATH = "Liuyan/img/cards/Pojun_skill.png"; // 图片路径
    private static final CardType TYPE = CardType.SKILL;
    private static final CardRarity RARITY = CardRarity.RARE; // 金色稀有卡
    private static final CardTarget TARGET = CardTarget.ALL_ENEMY;
    private static final CardColor COLOR = EXAMPLE_GREEN;

    private static final int BASE_VULNERABLE = 3; // 基础易伤层数
    private static final int UPGRADE_VULNERABLE = 5; // 升级后易伤层数
    private static final int STRENGTH_GAIN = 3; // 力量增益

    public PoJun() {
        super(ID, NAME, IMG_PATH, 1, DESCRIPTION, TYPE, COLOR, RARITY, TARGET);
        this.baseMagicNumber = BASE_VULNERABLE; // 易伤层数
        this.magicNumber = this.baseMagicNumber;
        this.exhaust = true; // 消耗
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        // 对所有敌人施加易伤
        CardCrawlGame.sound.play("POJUN");
        for (AbstractMonster monster : AbstractDungeon.getMonsters().monsters) {
            if (!monster.isDeadOrEscaped()) {
                this.addToBot(new ApplyPowerAction(
                        monster,
                        p,
                        new VulnerablePower(monster, this.magicNumber, false),
                        this.magicNumber
                ));
            }
        }



            this.addToBot(new ApplyPowerAction(p, p, new StrengthPower(p, this.magicNumber), this.magicNumber));
            this.addToBot(new ApplyPowerAction(p, p, new LoseStrengthPower(p, this.magicNumber), this.magicNumber));


        // 获得“立牧”能力
        this.addToBot(new ApplyPowerAction(
                p,
                p,
                new LimuPower(p)
        ));
    }

    @Override
    public void upgrade() {
        if (!this.upgraded) {
            this.upgradeName();
            this.upgradeMagicNumber(UPGRADE_VULNERABLE - BASE_VULNERABLE); // 升级后易伤层数增加
            this.rawDescription = cardStrings.UPGRADE_DESCRIPTION; // 更新描述
            this.initializeDescription();
        }
    }

    @Override
    public AbstractCard makeCopy() {
        return new PoJun();
    }
}
