package Liuyanmod.cards.liuyan;

import Liuyanmod.actions.XunshidaifaAction;
import basemod.abstracts.CustomCard;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.ui.panels.EnergyPanel;

import static Liuyanmod.characters.MyCharacter.PlayerColorEnum.EXAMPLE_GREEN;

public class Xunshidaifa extends CustomCard {
    public static final String ID = "Liuyanmod:Xunshidaifa";
    private static final CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings(ID);
    private static final String NAME = cardStrings.NAME;
    private static final String IMG_PATH = "Liuyan/img/cards/Xushidaifa_skill.jpg";
    private static final String DESCRIPTION = cardStrings.DESCRIPTION;
    private static final CardType TYPE = CardType.SKILL;
    private static final CardColor COLOR = EXAMPLE_GREEN;
    private static final CardRarity RARITY = CardRarity.RARE;
    private static final CardTarget TARGET = CardTarget.SELF;

    public Xunshidaifa() {
        super(ID, NAME, IMG_PATH, -1, DESCRIPTION, TYPE, COLOR, RARITY, TARGET);
        this.exhaust = true;
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        this.energyOnUse = EnergyPanel.totalCount;
        this.addToBot(new XunshidaifaAction(p, this.energyOnUse, this.upgraded));
    }


    @Override
    public void upgrade() {
        if (!this.upgraded) {
            this.upgradeName();
            this.retain = true;                       // 升级后保留

            // 切换到升级版描述，再刷新
            this.rawDescription = cardStrings.UPGRADE_DESCRIPTION;
            this.initializeDescription();
        }
    }

    public AbstractCard makeCopy() {
    return new Xunshidaifa();
  }
}
