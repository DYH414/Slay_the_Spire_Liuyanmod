package Liuyanmod.cards.liuyan;

import Liuyanmod.powers.GejuPower;
import Liuyanmod.powers.LimuPower;
import basemod.abstracts.CustomCard;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.GainEnergyAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;


import static Liuyanmod.characters.MyCharacter.PlayerColorEnum.EXAMPLE_GREEN;

public class Limu extends CustomCard {
    public static final String ID = "Liuyanmod:Limu";
    private static final CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings(ID);
    public static final String NAME = cardStrings.NAME;
    public static final String DESCRIPTION = cardStrings.DESCRIPTION;
    private static final String IMG_PATH = "Liuyan/img/cards/Limu.png"; // 改成你的图片路径
    private static final CardType TYPE = CardType.SKILL;
    private static final CardRarity RARITY = CardRarity.BASIC;
    private static final CardTarget TARGET = CardTarget.SELF;
    private static final CardColor COLOR = EXAMPLE_GREEN;
    private boolean toggle = false; // 交替音效用
    public Limu() {
        super(ID, NAME, IMG_PATH, 1, DESCRIPTION, TYPE, COLOR, RARITY, TARGET);
         this.baseMagicNumber =2;
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        // 播放音效（交替播放）
        String sfx = toggle ? "LIMU_1" : "LIMU_2";
        CardCrawlGame.sound.play(sfx,true);
        toggle = !toggle;
        this.addToBot(new GainEnergyAction(baseMagicNumber));
        this.addToBot(new ApplyPowerAction(p, p, new GejuPower(p,1)));
        // 下个回合不能打攻击牌（缠绕）
        this.addToBot(new ApplyPowerAction(p, p, new LimuPower(p)));

    }

    @Override
    public void upgrade() {
        if (!this.upgraded) {
            this.upgradeName();
            this.upgradeBaseCost(0);
            this.rawDescription = cardStrings.UPGRADE_DESCRIPTION;
            this.initializeDescription();
        }
    }

    @Override
    public AbstractCard makeCopy() {
        return new Limu();
    }
}
