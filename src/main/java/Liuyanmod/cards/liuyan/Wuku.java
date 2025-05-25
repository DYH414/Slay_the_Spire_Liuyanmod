package Liuyanmod.cards.liuyan;

import basemod.abstracts.CustomCard;
import com.megacrit.cardcrawl.actions.common.MakeTempCardInHandAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.helpers.CardLibrary;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

import static Liuyanmod.characters.MyCharacter.PlayerColorEnum.EXAMPLE_GREEN;

import java.util.Arrays;
import java.util.List;
import java.util.Random;

public class Wuku extends CustomCard {
    public static final String ID = "Liuyanmod:Wuku";
    private static final CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings(ID);
    public static final String NAME = cardStrings.NAME;
    public static final String DESCRIPTION = cardStrings.DESCRIPTION;
    public static final String IMG_PATH = "Liuyan/img/cards/Wuku_skill.jpg";

    private static final int COST = 1;
    private static final int UPGRADE_COST = 0;

    // 你定义的武器/坐骑/防具牌列表
    private static final List<String> CARD_POOL = Arrays.asList(
            "Liuyanmod:ZhuqueYushan",
            "Liuyanmod:ZhugeLiannu",
            "Liuyanmod:Qilingong",
            "Liuyanmod:Renwangdun",
            "Liuyanmod:Dilu",
            "Liuyanmod:Chitu",
            "Liuyanmod:Baguazhen",
            "Liuyanmod:Zhangbashemao",
            "Liuyanmod:Qinglongyanyuedao",
            "Liuyanmod:Qinggangjian"
    );

    public Wuku() {
        super(ID, NAME, IMG_PATH, COST, DESCRIPTION,
                CardType.SKILL, EXAMPLE_GREEN,
                CardRarity.UNCOMMON, CardTarget.SELF);
        this.exhaust = true;
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        Random rng = new Random();
        int index = rng.nextInt(CARD_POOL.size());
        String cardID = CARD_POOL.get(index);
        AbstractCard c = CardLibrary.getCopy(cardID);
        if (c != null) {
            c.setCostForTurn(0);
            addToBot(new MakeTempCardInHandAction(c, 1));
        }
    }

    @Override
    public void upgrade() {
        if (!upgraded) {
            upgradeName();
            upgradeBaseCost(UPGRADE_COST);
            initializeDescription();
        }
    }

    @Override
    public AbstractCard makeCopy() {
        return new Wuku();
    }
}
