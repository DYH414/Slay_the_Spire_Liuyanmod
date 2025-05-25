package Liuyanmod.cards.liuyan;

import basemod.abstracts.CustomCard;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.DrawCardAction;
import com.megacrit.cardcrawl.actions.common.GainEnergyAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

import static Liuyanmod.characters.MyCharacter.PlayerColorEnum.EXAMPLE_GREEN;

public class Chouliang extends CustomCard {
    public static final String ID = "Liuyanmod:Chouliang";
    private static final CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings(ID);
    public static final String NAME = cardStrings.NAME;
    public static final String DESCRIPTION = cardStrings.DESCRIPTION;
    public static final String IMG = "Liuyan/img/cards/Chouliang_skill.png";

    private static final int COST = 1;
    private static final CardType TYPE = CardType.SKILL;
    private static final CardColor COLOR = EXAMPLE_GREEN;
    private static final CardRarity RARITY = CardRarity.UNCOMMON;
    private static final CardTarget TARGET = CardTarget.SELF;
    private static final int DRAW = 3;
    private static final int UPGRADE_PLUS_DRAW = 1;

    public Chouliang() {
        super(ID, NAME, IMG, COST, DESCRIPTION, TYPE, COLOR, RARITY, TARGET);
        this.baseMagicNumber = this.magicNumber = DRAW;
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        // 抽牌
        this.addToBot(new DrawCardAction(p, this.magicNumber));
        System.out.println("Chouliang used, drew " + this.magicNumber + " cards");

        // 检查立牧效果
        if (p.hasPower("Liuyanmod:LimuPower")) {

            // 下一张卡费用为 0
            this.addToBot(new ApplyPowerAction(p, p, new Liuyanmod.powers.ChouliangPower(p, 1), 1));
            System.out.println("Chouliang triggered Limu effect: gained 1 energy, next card cost 0");
        }

        // 施加立牧
        this.addToBot(new ApplyPowerAction(p, p, new Liuyanmod.powers.LimuPower(p), 1));
        System.out.println("Chouliang applied 1 layer of LimuPower");
    }

    @Override
    public void upgrade() {
        if (!this.upgraded) {
            this.upgradeName();
            this.upgradeMagicNumber(UPGRADE_PLUS_DRAW);
            this.initializeDescription();
        }
    }

    @Override
    public AbstractCard makeCopy() {
        return new Chouliang();
    }
}