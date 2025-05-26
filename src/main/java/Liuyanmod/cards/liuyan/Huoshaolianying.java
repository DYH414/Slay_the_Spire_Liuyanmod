package Liuyanmod.cards.liuyan;

import basemod.abstracts.CustomCard;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;

import Liuyanmod.powers.ZhuoshaoPower;

import static Liuyanmod.characters.MyCharacter.PlayerColorEnum.EXAMPLE_GREEN;
import static com.megacrit.cardcrawl.core.CardCrawlGame.sound;

public class Huoshaolianying extends CustomCard {
    public static final String ID = "Liuyanmod:Huoshaolianying";
    private static final CardStrings strings = CardCrawlGame.languagePack.getCardStrings(ID);
    public static final String NAME = strings.NAME;
    public static final String DESCRIPTION = strings.DESCRIPTION;
    private static final String IMG = "Liuyan/img/cards/Huoshaolianying_skill.jpg";

    private static final int COST = 1;
    private static final CardType TYPE = CardType.SKILL;
    private static final CardColor COLOR = EXAMPLE_GREEN;
    private static final CardRarity RARITY = CardRarity.UNCOMMON;
    private static final CardTarget TARGET = CardTarget.ENEMY;

    private static final int BURN_BASE = 3;

    public Huoshaolianying() {
        super(ID, NAME, IMG, COST, DESCRIPTION, TYPE, COLOR, RARITY, TARGET);
        this.baseMagicNumber = this.magicNumber = BURN_BASE;
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        CardCrawlGame.sound.play("HUOSHAOLIANYING");
        if (m == null || m.isDeadOrEscaped()) return;

        // Step 1: 给目标施加基础灼烧
        this.addToBot(new ApplyPowerAction(m, p, new ZhuoshaoPower(m, this.magicNumber), this.magicNumber));

        // Step 2: 在所有动作完成后同步层数
        this.addToBot(new AbstractGameAction() {
            @Override
            public void update() {
                if (m.hasPower(ZhuoshaoPower.POWER_ID)) {
                    int burnAmount = m.getPower(ZhuoshaoPower.POWER_ID).amount;

                    for (AbstractMonster mo : AbstractDungeon.getMonsters().monsters) {
                        if (!mo.isDeadOrEscaped() && mo != m) { // 排除原始目标
                            addToTop(new ApplyPowerAction(mo, p,
                                    new ZhuoshaoPower(mo, burnAmount), burnAmount));
                        }
                    }
                }
                this.isDone = true;
            }
        });
    }


    @Override
    public void upgrade() {
        if (!upgraded) {
            upgradeName();
            this.upgradeMagicNumber(2); // 升级后变成 5 层初始灼烧
            initializeDescription();
        }
    }

    @Override
    public AbstractCard makeCopy() {
        return new Huoshaolianying();
    }
}
